(function() {
    var config = {
        componentUrl : 'components/',
        appName: 'myApp',
        version: requirejs.s.contexts._.config.version,
        buildTemplatePath: function(cmpName) {
            return config.componentUrl + cmpName + '.html?v=' + config.version;
        }
    };
    define(config);
})();