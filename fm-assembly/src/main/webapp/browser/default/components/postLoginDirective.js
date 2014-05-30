define(['require', 'app', 'config', 'underscore'], function (require, app, config, _) {
    app.compileProvider.directive('fmPostLogin', ['user', '$q', '$location', '$timeout', function (user, $q, $location, $timeout) {
        return {
            restrict: 'E',
            scope: true,
            controller: function ($scope, loader, $route) {
                var userObj = user.getLoginUser();
                $scope.userName = userObj.name;
                $scope.logOut = function () {
                    user.logOut();
                    $scope.$destroy();
                };
                configRoute(['calendar', 'manageUsers']);
                $route.reload();
                $scope.$on('$routeChangeStart', function() {
                    console.log('start');
                    loader.show();
                });
                $scope.$on('$routeChangeSuccess', function() {
                    console.log('stop');
                    loader.hide();
                });
                $scope.$on('session.timeout', function(e) {
                    user.logOut(false);
                });
                function configRoute(cmps) {
                    var serviceName = 'service';
                    _.forEach(cmps, function (cmp) {
                        app.routeProvider.when('/' + cmp, {
                            templateUrl: config.componentUrl + cmp + '.html',
                            controller: [serviceName, '$injector', function (service, $injector) {
                                $scope.service = service.$get($scope, $injector);
                                $scope.topMenu = cmp;
                            }],
                            resolve: resolveFactory(serviceName, cmp)
                        });
                    });
                    app.routeProvider.otherwise({redirectTo: '/' + cmps[0]});
                }

                function resolveFactory(serviceName, cmp) {
                    var resolve = {};
                    resolve[serviceName] = function () {
                        var deferred = $q.defer();
                        require([cmp], function (service) {
                            deferred.resolve(service);
                        });
                        return deferred.promise;
                    };
                    return resolve;
                }
            },
            templateUrl: config.componentUrl + 'postLoginTemplate.html',
            link: function (scope, el) {
            }
        }
    }]);
});