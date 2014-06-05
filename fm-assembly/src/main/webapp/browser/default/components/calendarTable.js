define(['app', 'config'], function (app, config) {
    var cmpName = 'calendarTable';
    app.compileProvider.directive(cmpName, [function () {
        return {
            restrict: 'E',
            scope: true,
            controller: function ($scope) {
                $scope.date = new Date();
                goFirstDateOfMonth(0);
                $scope.datesInWeak = [
                    'Sun', 'Mon', 'Tus', 'Wed', 'Thu', 'Fri', 'Sat'
                ];
                $scope.$watch('date', function() {
                    var weaks = [];
                    var curDate = $scope.date;
                    while (true) {
                        var daysOfCurWeak = [],
                            day = curDate.getDay(),
                            curDay;
                        for (curDay = 0; curDay <= 6; curDay++) {
                            (function() {
                                var date = goDate(curDate, curDay - day);
                                daysOfCurWeak[curDay] = {
                                    date: date,
                                    className: (date.getMonth() == $scope.date.getMonth()) ? '' : cmpName + '-outOfMonth'
                                };
                            })();
                        }
                        weaks.push(daysOfCurWeak);
                        curDate = nextDate(daysOfCurWeak[6].date);
                        if (curDate.getMonth() != $scope.date.getMonth()) {
                            break;
                        }
                    }

                    $scope.weaks = weaks;
                });
                this.clickDateItem = function(dateItem) {
                    console.log(dateItem);
                };
                this.prevMonth = function() {
                    goFirstDateOfMonth(-1);
                };
                this.nextMonth = function() {
                    goFirstDateOfMonth(1);
                };
                function goFirstDateOfMonth(diffMonth) {
                    var curDate = $scope.date;
                    $scope.date = new Date(curDate.getFullYear(), curDate.getMonth() + diffMonth, 1);
                }

                function nextDate(curDate) {
                    return goDate(curDate, 1)
                }

                function prevDate(curDate) {
                    return goDate(curDate, -1)
                }

                function goDate(curDate, diffDate) {
                    return new Date(curDate.getFullYear(), curDate.getMonth(), curDate.getDate() + diffDate);
                }
            },
            controllerAs: 'ctrl',
            templateUrl: config.buildTemplatePath(cmpName)
        };
    }]);
});
