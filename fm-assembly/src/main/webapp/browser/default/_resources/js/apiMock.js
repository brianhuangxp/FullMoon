define(['angular', 'config'], function (angular, config) {
    'use strict';
    angular.module(config.appName + '.api', [])
        .provider('userApi', function () {
            this.$get = ['$cookies', '$http', '$q', '$timeout', 'loader', function ($cookies, $http, $q, $timeout, loader) {
                return {
                    login: function (name, pass) {
                        loader.show();
                        var deferred = $q.defer();
                        $timeout(function () {
                            $cookies.sessid = '' + Math.random();
                            loader.hide();
                            deferred.resolve();
                        }, 1000);
                        return deferred.promise;
                    },
                    checkLogin: function () {
                        loader.show();
                        var deferred = $q.defer();
                        $timeout(function () {
                            loader.hide();
                            deferred.resolve(
                                {
                                    logined: $cookies.sessid != null,
                                    name: $cookies.user
                                }
                            );
                        }, 1000);
                        return deferred.promise;
                    },
                    logout: function () {
                        loader.show();
                        var deferred = $q.defer();
                        $timeout(function () {
                            loader.hide();
                            $cookies.sessid = undefined;
                            deferred.resolve();
                        }, 1000);
                        return deferred.promise;
                    }
                };
            }];
        })
        .provider('calendarApi', function () {
            this.$get = ['$cookies', '$http', '$q', '$timeout', 'loader', function ($cookies, $http, $q, $timeout, loader) {
                return {
                    listDatesOfMonth: function (year, month) {
                        var deferred = $q.defer();
                        loader.show();
                        $timeout(function() {
                            deferred.resolve([
                                {date: new Date(), eventsNum: 3}
                            ]);
                            loader.hide();
                        }, 1000);
                        return deferred.promise;
                    },
                    getDateInfo: function(year, month, date) {
                        var deferred = $q.defer();
                        loader.show();
                        $timeout(function() {
                            deferred.resolve({events: [
                                {info: 'test1', status: 0, id: 1},
                                {info: 'test2', status: 1, id: 2}
                            ]});
                            loader.hide();
                        }, 1000);
                        return deferred.promise;
                    }
                }
            }];
        });
});
