require.config({
    baseUrl: 'components',
    paths: {
        text: '../resources/js/lib/requireJs/text',
        app: '../resources/js/app',
        angular: '../resources/js/lib/angular/angular',
        'angular-route': '../resources/js/lib/angular/angular-route.min',
        'angular-cookies': '../resources/js/lib/angular/angular-cookies.min',
        underscore: '../resources/js/lib/underscore/underscore-min',
        common: '../resources/js/common',
        api: '../resources/js/api',
        config: '../resources/js/config'
    },
    shim: {
        'angular': {'exports': 'angular'},
        'underscore': {'exports': '_'},
        'angular-route': { deps: ['angular']},
        'angular-cookies': { deps: ['angular']}
    },
    urlArgs: 'v=0.1'
});
require(['angular','config', 'app'], function (angular, config) {
    angular.element(document).ready(
        function () {
            angular.bootstrap(document, [config.appName]);
        }
    );
});