define(['require', 'app', 'config', 'underscore'], function (require, app, config, _) {
    app.compileProvider.directive('fmPostLogin', ['user', '$q', '$location', function (user, $q, $location) {
        return {
            restrict: 'E',
            scope: true,
            controller: function ($scope, loader) {
                var userObj = user.getLoginUser();
                $scope.userName = userObj.name;
                $scope.logOut = function () {
                    user.logOut();
                    $scope.$destroy();
                };
                configRoute(['m1', 'manageUsers']);
                $scope.$on('$routeChangeStart', function() {
                    loader.show();
                });
                $scope.$on('$routeChangeSuccess', function() {
                    loader.hide();
                });
                $scope.$on('session.timeout', function(e) {
                   user.logOut(false);
                });
                function configRoute(cmps) {
                    var serviceName = 'service';
                    _.forEach(cmps, function (cmp) {
                        app.routeProvider.when('/' + cmp, {
                            templateUrl: config.componentUrl + cmp + 'Template.html',
                            controller: [serviceName, '$injector', function (service, $injector) {
                                $scope.service = service.$get($scope, $injector);
                                $scope.topMenu = cmp;
                            }],
                            resolve: resolveFactory(serviceName, cmp)
                        });
                    });
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
                var path = $location.path();
                path == '/login' && (path = '/welcome');
                $location.path('/');
                el.ready(function () {
                    scope.$apply(function () {
                        $location.path(path);
                    });
                });
            }
        }
    }]);
});