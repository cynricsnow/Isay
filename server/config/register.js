/**
 * Created by Ywl on 2015/10/14.
 */

var crypto = require('crypto');
var rand = require('csprng');
var mongoose = require('mongoose');
var user = require('../database/user_db');

exports.register = function(phone, password, callback) {
    //��һ����֤����֤
    //password��Ҫ��
    //if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/) && password.length > 4 && password.match(/[0-9]/) && password.match(/.[!,@,#,$,%,^,&,*,?,_,~]/)) {
    if (password.length >6) {
        var temp = rand(160, 36);//bts=160,radix=36
        var password2=temp+password;
        var token = crypto.createHash('sha512').update(phone+rand).digest("hex");
        var hashed_pwd = crypto.createHash('sha512').update(password2).digest("hex");

        var newUser = new user({
            token:token,
            status:false,
            phone:phone,
            signature:"����˺�����ʲô������˵",
            hashed_pwd:hashed_pwd,
            salt:temp
        });

        user.find({phone:phone}, function (err, users) {
            var len = users.length;

            if(len==0){
                newUser.save(function (err) {
                    callback({'response':"ע��ɹ�"});//������¼����
                });
            }else{
                callback({'response':"�û��Ѵ���"});
            }
        });
    }else{
        callback({'response':"����̫��"});
    }
};