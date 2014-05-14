define(['app', 'config'], function (app, config) {
    var cmpName = 'calendarTable';
    app.compileProvider.directive(cmpName, [function () {
        return {
            restrict: 'E',
            scope: true,
            controller: function ($scope) {
            },
            templateUrl: config.componentUrl + cmpName + '.html'
        };
    }]);
});
