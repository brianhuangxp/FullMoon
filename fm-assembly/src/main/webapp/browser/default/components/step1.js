define(['app', 'config', 'underscore'], function (app, config, _) {
    var cmpName = 'step1';
    app.compileProvider.directive(cmpName, [function () {
        return {
            restrict: 'A',
            scope: true,
            controller: function ($scope, $element, $attrs) {
                this.next = function () {
//                    $scope.next('new:step2');
                    $scope.next({
                        name: 'step2',    // can be function
                        restore: true    // can be function
                    });
                }
            },
            controllerAs:'ctrl',
            link:function(scope, el, attrs) {
            },
            templateUrl: config.buildTemplatePath(cmpName)
        };
    }]);
});
