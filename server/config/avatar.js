/**
 * Created by Ywl on 2015/10/19.
 */
var user = require('../database/user_db');

exports.set= function (id, data, contentType, callback) {
    user.find({token: id}, function (err, users) {
        if (users.length != 0) {
            users[0].avatar.data = data;
            users[0].contentType = contentType;
        } else {
            callback({'response': "异常错误，请退出重新登录"});
        }
    })
};

exports.get = function (id, callback) {
    user.find({token: id}, function (err, users) {
        if (users.length != 0) {
            callback({'response': "成功", 'res':users[0].toJSON});
        } else {
            callback({'response': "异常错误，请退出重新登录"});
        }
    })
}