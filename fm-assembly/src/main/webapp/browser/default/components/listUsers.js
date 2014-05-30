define(['app', 'config'], function (app, config) {
    var cmpName = 'listUsers';
    app.compileProvider.directive(cmpName, [function () {
        return {
            restrict: 'A',
            scope: true,
            controller: function ($scope) {
            },
            templateUrl: config.buildTemplatePath(cmpName)
        };
    }]);
});
