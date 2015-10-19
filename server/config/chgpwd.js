/**
 * Created by Ywl on 2015/10/14.
 */

var crypto = require('crypto');
var rand = require('csprng');
var mongoose = require('mongoose');
var user = require('../database/user_db');
var http = require('http');
var querystring = require('querystring');


//登录状态改密码
exports.chgpwd = function (id, old_pwd, new_pwd, callback) {
    var new_salt = rand(160, 36);
    var tmp_pwd1 = new_salt + new_pwd;
    var new_hashed_pwd = crypto.createHash('sha512').update(tmp_pwd1).digest("hex");

    user.find({token: id}, function (err, users) {
        if (users.length != 0) {
            var old_salt = users[0].salt;
            var old_hashed_pwd = users[0].hashed_pwd;
            var tmp_pwd2 = old_salt + old_pwd;
            var your_hashed_pwd = crypto.createHash('sha512').update(tmp_pwd2).digest("hex");


            if (old_hashed_pwd == your_hashed_pwd) {
                if (new_pwd.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/) && new_pwd.length > 4 && new_pwd.match(/[0-9]/) && new_pwd.match(/.[!,@,#,$,%,^,&,*,?,_,~]/)) {

                    user.findOne({token: id}, function (err, this_user) {
                        this_user.hashed_pwd = new_hashed_pwd;
                        this_user.salt = new_salt;
                        this_user.save();

                        callback({'response': "密码修改成功", 'res': true});
                    });
                } else {
                    callback({'response': "新密码太弱，请重试", 'res': false});
                }
            } else {
                callback({'response': "密码错误", 'res': false});
            }
        } else {
            //当前登录的用户服务器无记录，所以错误
            callback({'response': "异常错误，请退出重新登录", 'res': false});
        }
    });
};

exports.rstpwd_init = function (phone, callback) {
    var buffer = crypto.randomBytes(3);
    var num = parseInt(buffer.toString('hex'), 16);
    var length = 6;
    var code = (Array(length).join('0') + num).slice(-length);
    user.find({phone: phone}, function (err, users) {
        if (users.length != 0) {
            user.findOne({phone: phone}, function (err, this_user) {
                this_user.temp_str = code;
                this_user.save();

                var postData = {
                    uid:'a22UppfS6cm8',
                    pas:'ce3t7uep',
                    mob: phone,
                    con:'【Isay】您的验证码是：'+code+'1分钟内有效。如非您本人操作，可忽略本消息。',
                    type:'json'
                };
                var content = querystring.stringify(postData);

                var options = {
                    host:'api.weimi.cc',
                    path:'/2/sms/send.html',
                    method:'POST',
                    agent:false,
                    rejectUnauthorized : false,
                    headers:{
                        'Content-Type' : 'application/x-www-form-urlencoded',
                        'Content-Length' :content.length
                    }
                };
                var req = http.request(options,function(res){
                    res.setEncoding('utf8');
                    res.on('data', function (chunk) {
                        console.log(JSON.parse(chunk));
                    });
                    res.on('end',function(){
                        console.log('over');
                    });
                });
                req.write(content);
                req.end();
            });
        } else {
            callback({'response': "用户不存在", 'res': false});
        }
    });
};

exports.rstpwd_do = function (phone, code, new_pwd, callback) {
    user.find({phone: phone}, function (err, users) {
        if (users.length != 0) {
            var temp = users[0].temp_str;
            var new_salt = rand(160, 36);
            var tmp_pwd = new_salt + new_pwd;
            var new_hashed_pwd = crypto.createHash('sha512').update(tmp_pwd).digest("hex");

            if (temp == code) {
                //if (new_pwd.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/) && new_pwd.length > 4 && new_pwd.match(/[0-9]/) && new_pwd.match(/.[!,@,#,$,%,^,&,*,?,_,~]/)) {
                if (new_pwd.length > 6) {
                    user.findOne({phone: phone}, function (err, this_user) {
                        this_user.hashed_pwd = new_hashed_pwd;
                        this_user.salt = new_salt;
                        this_user.temp_str = "";
                        this_user.save();

                        callback({'response': "修改密码成功", 'res': true});
                        //之后跳转到登录界面，可以使表单为填写好的状态
                    });
                } else {
                    callback({'response': "新密码太弱，请重试", 'res': false});
                }
            } else {
                callback({'response': "验证码错误,请重新输入", 'res': false});
            }
        } else {
            callback({'response': "用户不存在", 'res': true});
        }
    });
};
