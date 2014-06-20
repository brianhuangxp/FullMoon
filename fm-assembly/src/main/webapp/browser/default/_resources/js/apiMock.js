define(['angular', 'config'], function (angular, config) {
    'use strict';
    angular.module(config.appName + '.api', [])
        .provider('userApi', function () {
            this.$get = ['$cookies', '$http', '$q', '$timeout', 'loader', '$resource',  function ($cookies, $http, $q, $timeout, loader, $resource) {
                return {
                    login: function (name, pass) {
                        return $http.post('/login', {user: name, pass: pass});
                    },
                    checkLogin: function () {
                       return $resource('/login/check').get({}).$promise;
                    },
                    logout: function () {
                        return $resource('/login/logout').get({}).$promise;
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
                    },
                    queryEvents: function(request) {
                        return $http.post('/events', {queryWords: request.queryWords, user: request.user});
                    }
                }
            }
        });
});
