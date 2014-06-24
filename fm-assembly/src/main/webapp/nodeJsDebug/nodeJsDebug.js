var os = require('os');
var express = require('express');
var bodyParser = require('body-parser');
var cookieParser = require('cookie-parser');
var formidable = require('formidable');
var session = require('express-session');
var ObjectID = require('mongodb').ObjectID;
var app = express();
app.use(bodyParser());
app.use(cookieParser());
app.use(session({secret: '1234567890QWERTY'}));


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
app.post('/login', function(req, res) {
    var user = req.body.user;
    var pass = req.body.pass;
    var myCollection = db.collection('users');
    req.session.user = null;
    myCollection.find({user: user}).toArray(function(err, items) {

        if (items[0] && items[0].pass == pass) {
            req.session.user = items[0].user;
            res.json({success: true});
        } else {
            res.json({success: false});
        }
    });
});
app.get('/login/check', function(req, res) {
    var user = req.session.user;
    if (user == null) {
        res.json({success: false});
    } else {
        res.json({success: true, user: user});
    }
});
app.get('/login/logout', function(req, res) {
    var user = req.session.user;
    req.session.user = null;
    res.json({success: true, user: user});
});
app.get('/events/:user/:from/:to', function(req, res) {
    var user = req.params.user;
    var from = req.params.from;
    var to = req.params.to;
    var criteria = {
        owner: user,
        dateString:{$gte: from, $lte: to}
    };
    var myCollection = db.collection('events');
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
app.delete('/event/:id', function(req, res) {
    var id = req.params.id;
    var criteria = {
        _id: new ObjectID(id)
    };
    var myCollection = db.collection('events');
    myCollection.remove(criteria, function() {
        res.json({success: true});
    });
});
app.post('/event/new', function(req, res) {
    var myCollection = db.collection('events');
    var doc = {
        status: 0,
        dateString: req.body.date,
        description:  req.body.description,
        owner:  req.body.user
    };
    myCollection.insert(doc, {safe: true}, function() {
        res.json({success: true});
    });
});
app.post('/events', function(req, res) {
    var myCollection = db.collection('events');
    var criteria = {
        description: {'$regex': req.body.queryWords},
        owner:  req.body.user
    };
    myCollection.find(criteria).sort({dateString: 1}).toArray(function(err, items) {
        res.json(items);
    });
});
app.post('/upload', function(req, res) {
    var form = new formidable.IncomingForm();
    form.parse(req, function(err, fields, files) {
        var path = files.audio.path;
        var eventId = fields.eventId;
        var criteria = {
            _id: new ObjectID(eventId)
        };
        var myCollection = db.collection('events');
        var patharr = path.split('\\');
        path = patharr[patharr.length - 1];
        myCollection.update(criteria, {$set:{path: path}}, function() {
            res.json({success: true, path: path});
        });
    });
});
app.get('/event/audio/:id', function(req, res) {
    var id = req.params.id;
    //todo security check
    var path = os.tmpdir();
    res.sendfile(path + '/' + id);
});

app.listen(8089);