require(['angular','config', 'app'], function (angular, config) {
    angular.element(document).ready(
        function () {
            angular.bootstrap(document, [config.appName]);
        }
    );
});