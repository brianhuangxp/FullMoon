define(['app', 'config'], function (app, config) {
    app.compileProvider.directive('fmLogin', [function () {
        return {
            restrict: 'E',
            controller: function ($scope, user) {
                $scope.login = function () {
                    user.login($scope.userName, $scope.password);
                };
            },
            templateUrl: config.buildTemplatePath('loginTemplate')
        }
    }]);
});
