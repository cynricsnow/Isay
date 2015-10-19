/**
 * Created by Ywl on 2015/10/14.
 */
// module dependencies
var express = require('express');
var app = express();
var port = process.env.PORT || 3000;
var logger = require('morgan');
var bodyParser = require('body-parser');

// configuration
app.use(express.static(__dirname + '/public'));
app.use(logger);
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended:true
}));

// routes
require('./routes/routes.js')(app);

app.listen(port);

console.log('The app is listening on port '+port);
