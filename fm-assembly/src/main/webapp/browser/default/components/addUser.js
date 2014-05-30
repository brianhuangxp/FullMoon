define(['app', 'config'], function (app, config) {
    var cmpName = 'addUser';
    app.compileProvider.directive(cmpName, ['$q', '$compile','popupMsg', function ($q, $compile, popupMsg) {
        return {
            restrict: 'A',
            scope: true,
            controller: ['$scope', function ($scope) {
                var closeDeferred;
                this.add = function () {
                    $scope.$emit('close');
                };
                this.cancel = function () {
                    //show confirm window
                    var promise = popupMsg.alert({
                        msg: 'sure?'
                    });
                    $scope.$emit('close', promise);
                };
                $scope.$emit('safeClose', this.cancel);
            }],
            controllerAs: 'ctrl',
            link: function (scope, el, attrs, ctrl) {
            },
            templateUrl: config.buildTemplatePath(cmpName)
        };
    }]);
});
