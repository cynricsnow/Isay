/**
 * Created by Ywl on 2015/10/19.
 */

var fs = require('fs'),
    request = require('request');

var download = function(uri, filename, callback){
    request.head(uri, function(err, res, body){
        //console.log('content-type:', res.headers['content-type']);
        //console.log('content-length:', res.headers['content-length']);

        request(uri).pipe(fs.createWriteStream(filename)).on('close', callback);
    });
};

download('http://www.gravatar.com/avatar/8ed99e6bab0625ffd91b350b65b9929b?s=200&r=pg&d=identicon', 'google.png', function(){
    console.log('done');
});
