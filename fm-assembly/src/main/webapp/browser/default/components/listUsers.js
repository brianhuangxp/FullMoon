define(['app', 'config'], function (app, config) {
    app.compileProvider.directive('listUsers', [function () {
        return {
            restrict: 'A',
            scope: true,
            controller: function ($scope) {
            },
            templateUrl: config.componentUrl + 'listUsers.html'
        };
    }]);
});
