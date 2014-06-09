var require;     // use require global variable, so that the request of main.js can also have a version control
(function() {
    var version = '0.12';
    var nodeJsDebug = false;
    require = {
        baseUrl: 'components',
        paths: {
            text: '../resources/js/lib/requireJs/text',
            app: '../resources/js/app',
            angular: '../resources/js/lib/angular/angular',
            'angular-route': '../resources/js/lib/angular/angular-route.min',
            'angular-cookies': '../resources/js/lib/angular/angular-cookies.min',
            'angular-resource': '../resources/js/lib/angular/angular-resource.min',
            underscore: '../resources/js/lib/underscore/underscore-min',
            directives: '../resources/js/directives',
            providers: '../resources/js/providers',
            api: '../resources/js/api' + (nodeJsDebug ? 'Mock' : ''),
            config: '../resources/js/config'
        },
        shim: {
            'angular': {'exports': 'angular'},
            'underscore': {'exports': '_'},
            'angular-route': { deps: ['angular']},
            'angular-cookies': { deps: ['angular']},
            'angular-resource': { deps: ['angular']}
        },
        version: version,
        urlArgs: 'v=' + version
    };
})();