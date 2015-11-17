/**
 * Created by Ywl on 2015/10/14.
 */

var mongoose = require('mongoose');

var Schema = mongoose.Schema;

var userSchema = mongoose.Schema({
    token:String,
    status:Boolean,//登录状态
    phone:String,
    email:String, //由用户后续选择绑定
    avatar:{ data: Buffer, contentType: String }, //用户头像
    nick_name:String,
    gender:Boolean,//true:male, false:female
    birthday:Date,
    age:Number, //由birthday决定
    signature:String,
    job:String,
    education:String,
    location:String,
    industry:String,
    hashed_pwd:String,
    salt:String,
    temp_str:String
});

mongoose.connect('mongodb://localhost:27017/user');
module.exports = mongoose.model('users', userSchema);