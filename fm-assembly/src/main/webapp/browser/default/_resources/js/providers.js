define(['angular', 'config', 'underscore', 'require', 'api'], function (angular, config, _, require) {
    'use strict';
    angular.module(config.appName + '.providers', [])
        .provider('user', function () {
            var isAdmin = false;
            var loginUser = null;
            var hasLogin = undefined;
            this.asAdmin = function () {
                isAdmin = true;
            };
            this.getLoginUser = function () {
                return {
                    name: loginUser,
                    isAdmin: isAdmin
                }
            };

            this.$get = ['$cookies' , 'userApi', '$rootScope', function ($cookies, userApi, $rootScope) {
                return {
                    hasLogin: function () {
                        return hasLogin;
                    },
                    checkLogin: function () {
                        userApi.checkLogin().then(function (o) {
                            console.log('checkLogin', o.success);
                            hasLogin = o.success;
                            loginUser = o.user;
                        });
                    },
                    isAdmin: function () {
                        return hasLogin && isAdmin;
                    },
                    login: function (user, pass) {
                        var promise = userApi.login(user, pass);
                        promise.then(function (o) {
                            hasLogin = o.data.success;
                            loginUser = user;
                            $cookies.user = user;
                        });
                        return promise;
                    },
                    logOut: function (remote) {
                        hasLogin = false;
                        loginUser = null;
                        if (false === remote) {
                            return;
                        }
                        userApi.logout();
                    },
                    getLoginUser: function () {
                        return {
                            name: loginUser,
                            isAdmin: isAdmin
                        };
                    }
                }
            }];
        })
        .provider('loader', function () {
            this.$get = function ($compile, $rootScope) {
                var counter = 0;
                var dom = null;
                return {
                    show: function () {
                        counter++;
                        counter == 1 && showLoader();
                    },
                    hide: function () {
                        counter && counter--;
                        counter || hideLoader();
                    }
                };
                function hideLoader() {
                    dom && dom.remove();
                }

                function showLoader() {
                    dom = angular.element(document.createElement('div'));
                    dom.addClass('fmSpinLoader');
                    angular.element(document.body).append($compile(dom)($rootScope));
                }
            };
        })
        .provider('cssLoader', function () {
            this.$get = function ($log) {
                var loadedCss = [];

                function hasLoaded(css) {
                    return (loadedCss.indexOf(css) != -1);
                }

                return {
                    load: function (css) {
                        if (hasLoaded(css)) {
                            $log.info('css', css, 'has already loaded');
                            return;
                        }
                        var cssPath = config.componentUrl + css + '.css?v=' + config.version;
                        if (document.createStyleSheet) {
                            document.createStyleSheet(cssPath);
                        } else {
                            var link = document.createElement("link");
                            link.type = "text/css";
                            link.rel = "stylesheet";
                            link.href = cssPath;
                            document.getElementsByTagName("head")[0].appendChild(link);
                        }
                        loadedCss.push(css);
                    }
                };
            };
        })
        .provider('popupMsg', function () {
            function fn($compile, $rootScope, $q) {
                return {
                    alert: function (config) {
                        var scope = buildScope(config);
                        scope.title = scope.title || 'alert';
                        display('fm-popup-alert', scope);
                        return scope.deferred.promise;
                    },
                    confirm: function (config) {
                        var scope = buildScope(config);
                        scope.title = scope.title || 'confirm';
                        display('fm-popup-confirm', scope);
                        return scope.deferred.promise;
                    }
                };

                function buildScope(config) {
                    var msg = config.msg;
                    var deferred = $q.defer();
                    var scope = $rootScope.$new();
                    scope.deferred = deferred;
                    scope.msg = msg;
                    scope.title = config.title;
                    return scope;
                }

                function display(directive, scope) {
                    angular.element(document.body).append($compile('<div ' + directive + '></div>')(scope));
                }
            }

            this.$get = ['$compile', '$rootScope', '$q', fn];
        })
        .factory('ajaxService', function ($http, $q, $rootScope, loader) {
            // todo add client cache.
            return{
                send: function (option) {
                    var deferred = $q.defer();
                    loader.show();
                    $http({
                            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                            url: option.url,
                            method: "post",
                            data: option.requestData,
                            transformRequest: function(obj) {
                                var str = [];
                                for (var p in obj)
                                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                                return str.join("&");
                            }
                        }
                    ).success(
                        function (data, status, headers, config) {
                            if (data.status.statusCode) {
                                deferred.resolve(data, status, headers, config);
                            } else {
                                deferred.reject(data, status, headers, config);
                            }
                            loader.hide();
                        }).error(function (data, status, headers, config) {
                            alert(status);
                            deferred.reject(data, status, headers, config);
                            loader.hide();
                        }
                    );
                    return deferred.promise;
                }
            }
        }
    )
});
