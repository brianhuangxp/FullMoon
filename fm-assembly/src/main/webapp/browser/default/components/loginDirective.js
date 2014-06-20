define(['app', 'config'], function (app, config) {
    app.compileProvider.directive('fmLogin', [function () {
        return {
            restrict: 'E',
            controller: function ($scope, user) {
                $scope.login = function () {
                    user.login($scope.userName, $scope.password).then(function(o) {
                        if (!o.data.success) {
                            alert('fail');
                        }
                    });
                };
            },
            templateUrl: config.buildTemplatePath('loginTemplate')
        }
    }]);
});
