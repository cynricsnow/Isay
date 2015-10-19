/**
 * Created by Ywl on 2015/10/15.
 */
var user = require('../database/user_db');
var md5 = require('blueimp-md5').md5;
var request = require('request');

exports.bindemail = function(id, email, callback){
    user.find({token: id}, function (err, users1) {
        if(users1.length!=0){
            user.find({email: email}, function (err, users2) {
                if(users2.length == 0){
                    users1[0].email = email;
                    //加入头像
                    var email_hash = md5(email.trim().toLowerCase());
                    var avatar_url = 'http://www.gravatar.com/avatar/'+email_hash+'?s=200&r=pg&d=identicon';
                    request({
                        url: avatar_url,
                        encoding: 'binary'
                    }, function(error, response, body) {
                        if (!error && response.statusCode === 200) {
                            body = new Buffer(body, 'binary');
                            users1[0].img.data=body;
                            users1[0].img.contentType='image/png';
                        }
                    });
                    callback({'response':"绑定邮箱成功", 'res':true})
                }
                else{
                    callback({'response':"这个邮箱已经被绑定", 'res':false})
                }
            });
        }else{
            callback({'response':"异常错误，请退出重新登录"});
        }
    });
};