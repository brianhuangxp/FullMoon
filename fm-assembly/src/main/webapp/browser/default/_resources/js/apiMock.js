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
            this.$get = function (loader, $q, $resource, $http) {
                return {
                    listDatesOfMonth: function (request) {
                        var Events = $resource('/events/:user/:from/:to');
                        return Events.query({
                            user: request.user,
                            from: request.from,
                            to: request.to
                        }).$promise;
                    },
                    getDateInfo: function(request) {
                        var Events = $resource('/events/:user/:date');
                        return Events.query({
                            user: request.user,
                            date: request.date
                        }).$promise;
                    },
                    rescheduleEvent: function(request) {
                        var Events = $resource('/event/:id/reschedule/:date');
                        return Events.get({
                            id: request.id,
                            date: request.date
                        }).$promise;
                    },
                    deleteEvent: function(request) {
                        var Events = $resource('/event/:id');
                        return Events.delete({
                            id: request.id
                        }).$promise;
                    },
                    updateEventDesciption: function(request) {
                        return $http.post('/event/description/' + request.id, {description: request.description});
                    },
                    addNewEvent: function(request) {
                        return $http.post('/event/new/', {date: request.date, description: request.description,  user: request.user});
                    },
                    updateStatus: function(request) {
                        var Events = $resource('event/:id/status/:status');
                        return Events.get({
                            id: request.id,
                            status: request.status
                        }).$promise;
                    }
                }
            }
        });
});
