/**
 * Created by Ywl on 2015/10/19.
 */
var user = require('../database/user_db');

exports.logout= function (id, callback) {
    user.find({token: id}, function (err, users) {
        if (users.length != 0) {
            users[0].status=false;
            callback({'response': "退出成功", 'res': true});
        };
    })
};