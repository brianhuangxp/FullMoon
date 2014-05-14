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

        }).directive('fmMain', function ($compile, user, $location) {
            return {
                restrict: 'E',
                controller: function ($scope) {
                    $scope.user = user; //used for watch
                },
                link: function (scope, elm, attrs) {
                    user.checkLogin();
                    scope.$watch('user.hasLogin()', function (hasLogin) {
                        elm.empty();
                        if (hasLogin === undefined) {
                            elm.html('please wait...');
                        } else if (!hasLogin) {
                            require(['loginDirective'], function () {
                                //quick login. todo remove
                                scope.userName = scope.password = 'root';

                                elm.append($compile('<fm-login/>')(scope));
                                $location.path('login');
                            });
                        } else {
                            require(['postLoginDirective'], function () {
                                elm.append($compile('<fm-post-login/>')(scope));
                            });
                        }
                    });
                }
            };
        }
    );
    return app;
});