var express = require('express');
var router = express.Router();
var path    = require("path");

/* GET home page. */
router.get('/', function(req, res, next) {
  res.sendFile(path.join(__dirname + '/../views/index.html'));
});

router.get('/json', function(req, res, next){
  var data = {name:"abcname", data:"abcdata"};
  res.send(JSON.stringify(data));
});

module.exports = router;
