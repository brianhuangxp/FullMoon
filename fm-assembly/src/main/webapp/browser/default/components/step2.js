define(['app', 'config'], function (app, config) {
    app.compileProvider.directive('step2', [function () {
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
            templateUrl: config.componentUrl + 'step2.html'
        };
    }]);
});
