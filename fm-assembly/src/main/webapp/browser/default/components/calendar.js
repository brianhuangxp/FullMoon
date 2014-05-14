define(['app', 'angular', 'calendarTable'], function (app, angular) {
    return {
        $get: function(scope, $injector) {
            return $injector.invoke(['$rootScope', function($rootScope) {
                return {
                    btimeout: function() {
                        $rootScope.$broadcast('session.timeout');
                    }
                };
            }]);
        }
    };
});