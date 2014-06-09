var express = require('express');
var bodyParser = require('body-parser');
var ObjectID = require('mongodb').ObjectID;
var app = express();
app.use(bodyParser());

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

app.get('/events/:user/:date/reschedule/:newdate', function(req, res) {
    var user = req.params.user;
    var dateString = req.params.date;
    var newDateString = req.params.newdate;
    var criteria = {
        owner: user,
        dateString:dateString
    };
    var myCollection = db.collection('events');
    myCollection.update(criteria, {$set:{dateString: newDateString}}, {multi: true}, function() {
        res.json({success: true});
    });
});
app.get('/event/:id/reschedule/:newdate', function(req, res) {
    var id = req.params.id;
    var newDateString = req.params.newdate;
    var criteria = {
        _id: new ObjectID(id)
    };
    var myCollection = db.collection('events');
    myCollection.update(criteria, {$set:{dateString: newDateString}}, function() {
        res.json({success: true});
    });
});
app.get('/event/:id/status/:status', function(req, res) {
    var id = req.params.id;
    var status = req.params.status;
    var criteria = {
        _id: new ObjectID(id)
    };
    var myCollection = db.collection('events');
    myCollection.update(criteria, {$set:{status: status}}, function() {
        res.json({success: true});
    });
});

app.post('/event/description/:id', function(req, res) {
    var id = req.params.id;
    var criteria = {
        _id: new ObjectID(id)
    };
    var myCollection = db.collection('events');
    myCollection.update(criteria, {$set:{description: req.body.description}}, function() {
        res.json({success: true});
    });
});

app.listen(8089);