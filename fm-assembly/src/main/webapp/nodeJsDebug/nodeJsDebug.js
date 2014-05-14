var express = require('express');
var server = express();
server.use(express.static(__dirname + '/../'));
server.use('/resources', express.static(__dirname + '/../browser/default/_resources/'));
server.use('/components', express.static(__dirname + '/../browser/default/components/'));

server.listen(8089);