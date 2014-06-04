define(['angular', 'require', 'config', 'common', 'angular-route', 'angular-cookies'], function (angular, require, config) {
    'use strict';
    var app = angular.module(config.appName, [
        'ngRoute',
        'ngCookies',
        'myApp.api',
        'myApp.common'
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