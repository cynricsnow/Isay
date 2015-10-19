/**
 * Created by Ywl on 2015/10/15.
 */

var user = require('../database/user_db');

exports.set = function (id, nick_name, gender, birthday, signature, job, education, location, industry, callback) {
    user.find({token: id}, function (err, users) {
        if (users.length != 0) {
            users[0].nick_name = nick_name;
            users[0].gender = gender;
            users[0].birthday = birthday;
            var today = new Date();
            var age = today.getFullYear() - birthday.getFullYear();
            var m = today.getMonth() - birthday.getMonth();
            if (m < 0 || (m === 0 && today.getDate() < birthday.getDate())) {
                age--;
            }
            users[0].age = age;
            users[0].signature = signature;
            users[0].job = job;
            users[0].education = education;
            users[0].location = location;
            users[0].industry = industry;
        } else {
            callback({'response': "异常错误，请退出重新登录"});
        }
    });
};

exports.get = function (id, callback) {
    user.find({token: id}, function (err, users) {
        if (users.length != 0) {
            callback({'response': "成功", 'res':users[0].toJSON});
        } else {
            callback({'response': "异常错误，请退出重新登录"});
        }
    });
};