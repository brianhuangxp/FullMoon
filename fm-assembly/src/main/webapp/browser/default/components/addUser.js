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
                    closeDeferred = $q.defer();
                    //show confirm window
                    popupMsg.confirm({
                        msg: 'sure?',
                        deferred: closeDeferred
                    });
                    $scope.$emit('close', closeDeferred.promise);
                };
                $scope.$emit('safeClose', this.cancel);
            }],
            controllerAs: 'ctrl',
            link: function (scope, el, attrs, ctrl) {
            },
            templateUrl: config.componentUrl + cmpName + '.html'
        };
    }]);
});
