define(['angular', 'config'], function (angular, config) {
    'use strict';
    angular.module(config.appName + '.api', [])
        .provider('userApi', function () {
            this.$get = ['$cookies', '$http', '$q', '$timeout', 'loader', 'ajaxService', function ($cookies, $http, $q, $timeout, loader, ajaxService) {
                return {
                    login: function (name, pass) {
                        var deferred = $q.defer();
                        ajaxService.send({
                            url: 'system/system.login',
                            requestData: {
                                userName: name,
                                userPwd: pass
                            }
                        }).then(function (data, status, headers, config) {
                                if (data.status.statusCode == 1) {
                                    deferred.resolve();
                                } else {
                                    alert("User and password don't match!");
                                }
                            }, function (data, status, headers, config) {
                                alert("Happen Error!")
                            }
                        );
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
                        var deferred = $q.defer();
                        ajaxService.send({
                            url: 'system/system.logout'
                        }).then(function (data, status, headers, config) {
                                if (data.status.statusCode == 1) {
                                    $cookies.sessid = undefined;
                                    deferred.resolve();
                                } else {
                                    alert('Logout fail');
                                }
                            }, function (data, status, headers, config) {
                                alert('Logout fail');
                            }
                        );
                        return deferred.promise;
                    }
                };
            }];
        });
});
