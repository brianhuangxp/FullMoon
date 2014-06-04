define(['angular', 'require', 'config', 'providers', 'directives', 'angular-route', 'angular-cookies'], function (angular, require, config) {
    'use strict';
    var app = angular.module(config.appName, [
        'ngRoute',
        'ngCookies',
        config.appName + '.api',
        config.appName + '.providers',
        config.appName + '.directives'
    ]).config(function (userProvider, $compileProvider, $controllerProvider, $routeProvider, $locationProvider) {
            userProvider.asAdmin();
            app.compileProvider = $compileProvider;
            app.controllerProvider = $controllerProvider;
            app.routeProvider = $routeProvider;
            app.locationProvider = $locationProvider;
        }
    );
    return app;
});