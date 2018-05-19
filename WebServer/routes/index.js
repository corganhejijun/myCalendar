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
    if (param.hasOwnProperty("register")){
      register(res, param);
    }
    else{
      login(res, param);
    }
  }
});

function login(res, param){
  var username = param.username;
  var passowrd = param.password;
  var sqlStr = "SELECT * FROM 'user' WHERE name='" + username + "' AND password='" + passowrd + "'";
  console.log(sqlStr);
  db.all(sqlStr, function(err, rows){
    if (err == null && rows.length == 1)
      res.send(JSON.stringify({event:"login", result:true}));
    else
      res.send(JSON.stringify({event:"login", result:false, msg:err}));
  });
}

function register(res, param){
  var username = param.username;
  var passowrd = param.password;
  var sqlStr = "INSERT INTO 'user' (name,password) VALUES ('" + username + "','" + passowrd + "')";
  console.log(sqlStr);
  db.run(sqlStr, function(err){
    if (err != null){
      res.send(JSON.stringify({event:"register", result:false, msg:err}));
    }
    else{
      res.send(JSON.stringify({event:"register", result:true}));
    }
  })
}

module.exports = router;
