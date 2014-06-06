var express = require('express');
var app = express();

//init db connection
var MongoClient = require('mongodb').MongoClient;
var db;
MongoClient.connect('mongodb://127.0.0.1:27017/fullMoon', function(err, dbo) {
    if (err)
        throw err;
    console.log("connected to the mongoDB !");
    db = dbo;
});

//serve static
app.use(express.static(__dirname + '/../'));
app.use('/resources', express.static(__dirname + '/../browser/default/_resources/'));
app.use('/components', express.static(__dirname + '/../browser/default/components/'));

//serve rest api
app.get('/events/:user/:from/:to', function(req, res) {
    var user = req.params.user;
    var from = req.params.from;
    var to = req.params.to;
    var criteria = {
        owner: user,
        dateString:{$gte: from, $lte: to}
    };
    var myCollection = db.collection('events');
    myCollection.find(criteria).toArray(function(err, items) {
//        res.json(items);
    });
    myCollection.group(['dateString'], criteria, {count: 0}, function (obj, prev) {
        prev.count++;
    }, function(err, results) {
        res.json(results);
    })
});

app.get('/events/:user/:date', function(req, res) {
    var user = req.params.user;
    var dateString = req.params.date;
    var criteria = {
        owner: user,
        dateString:dateString
    };
    var myCollection = db.collection('events');
    myCollection.find(criteria).toArray(function(err, items) {
        res.json(items);
    });
});

app.post('/event/:id', function(req, res) {
    var id = req.params.id;
});

app.listen(8089);