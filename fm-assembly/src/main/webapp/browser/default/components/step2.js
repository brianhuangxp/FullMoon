define(['app', 'config'], function (app, config) {
    var cmpName = 'step2';
    app.compileProvider.directive(cmpName, [function () {
        return {
            restrict: 'A',
            scope: true,
            controller: function ($scope, $element, $attrs) {
                this.next = function() {
                    $scope.next();
                };
                this.back = function() {
                    $scope.back('new:step1');
                };
            },
            controllerAs: 'ctrl',
            link: function() {

            },
            templateUrl: config.buildTemplatePath(cmpName)
        };
    }]);
});
