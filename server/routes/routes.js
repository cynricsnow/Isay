/**
 * Created by Ywl on 2015/10/14.
 */

var chgpwd = require('../config/chgpwd');
var register = require('../config/register');
var login = require('../config/login');
var logout = require('../config/logout');
var bindemail = require('../config/bindemail');
var profile = require('../config/profile');
var avatar = require('../config/avatar');

module.exports = function (app) {

    app.get('/', function (req, res) {
        res.end('Server-side-of-Isay');
    });

    app.post('/login', function (req, res) {
        var phone = req.body.phone;
        var password = req.body.password;

        login.login(phone, password, function (found) {
            console.log(found);
            res.json(found);
        });
    });

    app.post('/register', function (req, res) {
        var phone = req.body.phone;
        var password = req.body.password;

        register.register(phone, password, function (found) {
            console.log(found);
            res.json(found);
        });
    });

    app.post('/logout', function(req, res) {
        var id = req.body.id;
        logout.logout(id, function (found) {
            console.log(found);
            res.json(found);
        });
    });

    app.post('/api/chgpwd', function (req, res) {
        var id = req.body.id;
        var old_pwd = req.body.oldpwd;
        var new_pwd = req.body.newpwd;

        chgpwd.chgpwd(id, old_pwd, new_pwd, function (found) {
            console.log(found);
            res.json(found);
        });
    });

    app.post('/api/rstpwd/init', function(req, res) {
        var phone = req.body.phone;

        chgpwd.rstpwd_init(phone, function (found) {
            console.log(found);
            res.json(found);
        });
    });

    app.post('/api/rstpwd/do', function(req, res) {
        var phone = req.body.phone;
        var code = req.body.code;
        var new_pwd = req.body.newpwd;

        chgpwd.rstpwd_do(phone, code, new_pwd, function(found) {
            console.log(found);
            res.json(found);
        });
    });

    app.post('/api/bindemail', function (req, res) {
        var id = req.body.id;
        var email = req.body.phone;

        bindemail.bindemail(id, email, function(found) {
            console.log(found);
            res.json(found);
        });
    });

    app.post('/api/profile', function(req, res) {

        var id = req.body.id,
            nick_name = req.body.nick_name,
            gender = req.body.gender,
            birthday = req.body.birthday,
            signature = req.body.signature,
            job = req.body.job,
            education = req.body.education,
            location = req.body.mylocation,
            industry = req.body.industry;

        profile.set(id, nick_name, gender, birthday, signature, job, education, location, industry, function(found) {
            console.log(found);
            res.json(found);
        });
    });

    app.get('/api/profile', function(req, res){
        var id = req.body.id;
        profile.get(id, function(found) {
            console.log(found);
            res.json(found);
        })
    })
    
    app.post('/api/avatar', function (req, res) {
        var id = req.body.id,
            data = req.body.img.data,
            contentType = req.body.img.contentType;

        avatar.set(id, data, contentType, function(found) {
            console.log(found);
            res.json(found);
        });
    });

};