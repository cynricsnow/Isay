/**
 * Created by Ywl on 2015/10/14.
 */


var crypto = require('crypto');
var rand = require('csprng');
var mongoose = require('mongoose');
var user = require('../database/user_db');


exports.login= function (phone, password, callback) {
    user.find({phone:phone}, function(err, users){
        if(users.length!=0){
            var temp=users[0].salt;
            var hashed_pwd = users[0].hashed_pwd;
            var id = users[0].token;
            var tmp_pwd=temp+password;
            var your_hash_pwd=crypto.createHash('sha512').update(tmp_pwd).digest('hex');
            if(hashed_pwd==your_hash_pwd){
                users[0].status=true;
                callback({'response':"登录成功", 'res':true, 'token':id});
            }else{
                callback({'response':"密码错误", 'res':false});
            }
        }
        else{
            callback({'response':"用户不存在", 'res':false});
        }
    });
};
