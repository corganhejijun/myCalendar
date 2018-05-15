var express = require('express');
var router = express.Router();
var path    = require("path");
var sqlite3 = require('sqlite3');

var db = new sqlite3.Database(path.join(__dirname, "..", 'data.db'));

/* GET home page. */
router.get('/', function(req, res, next) {
  res.sendFile(path.join(__dirname, '/../views/index.html'));
});

router.get('/json', function(req, res, next){
  var param = req.query;
  if (param.hasOwnProperty("username")){
    var username = param.username;
    var passowrd = param.passowrd;
    var sqlStr = "SELECT * FROM 'user' WHERE name='" + username + "' AND password='" + passowrd + "'";
    console.log(sqlStr);
    db.all(sqlStr, function(err, rows){
      if (!err && rows.length == 1)
        res.send(JSON.stringify({result:true}));
      else
        res.send(JSON.stringify({result:false, msg:err}));
    });
  }
});

module.exports = router;
