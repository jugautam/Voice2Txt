var express = require('express');
var app = express();



app.get('/', function(req, res){
	//res.set('Content-Type', 'application/json');
	

	var fpcalc = require("fpcalc");
fpcalc("./audio.mp3", function(err, result) {

    if (err) throw err;
    console.log(result.file, result.duration, result.fingerprint);
    res.send(result.fingerprint);
});

//res.send('hello');

});	



var server = app.listen(3000, function() {
    console.log('Listening on port %d', server.address().port);
});