/**
 * Created by Ywl on 2015/10/14.
 */

var crypto = require('crypto');
var rand = require('csprng');
var mongoose = require('mongoose');
var user = require('../database/user_db');
var http = require('http');
var querystring = require('querystring');


//��¼״̬������
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

                        callback({'response': "�����޸ĳɹ�", 'res': true});
                    });
                } else {
                    callback({'response': "������̫����������", 'res': false});
                }
            } else {
                callback({'response': "�������", 'res': false});
            }
        } else {
            //��ǰ��¼���û��������޼�¼�����Դ���
            callback({'response': "�쳣�������˳����µ�¼", 'res': false});
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
                    con:'��Isay��������֤���ǣ�'+code+'1��������Ч����������˲������ɺ��Ա���Ϣ��',
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
            callback({'response': "�û�������", 'res': false});
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

                        callback({'response': "�޸�����ɹ�", 'res': true});
                        //֮����ת����¼���棬����ʹ��Ϊ��д�õ�״̬
                    });
                } else {
                    callback({'response': "������̫����������", 'res': false});
                }
            } else {
                callback({'response': "��֤�����,����������", 'res': false});
            }
        } else {
            callback({'response': "�û�������", 'res': true});
        }
    });
};
