define(['app', 'config', 'underscore', 'calendarTableEvent'], function (app, config, _) {
    var cmpName = 'calendarTable';
    app.compileProvider
        .directive(cmpName, function () {
        return {
            restrict: 'E',
            scope: true,
            controller: function ($scope, calendarApi, $document, loader, $filter, user) {
                $scope.date = new Date();
                goFirstDateOfMonth(0);
                $scope.datesInWeak = [
                    'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'
                ];
                $scope.$watch('currentEvent', function() {
                    console.log($scope.currentEvent)
                });
                $scope.$watch('date', function() {
                    var weaks = [];
                    var curDate = $scope.date;
                    var datesHash = {};
                    $scope.datesHash = datesHash;
                    while (true) {
                        var daysOfCurWeak = [],
                            day = curDate.getDay(),
                            curDay;
                        for (curDay = 0; curDay <= 6; curDay++) {
                            (function() {
                                var date = goDate(curDate, curDay - day);
                                daysOfCurWeak[curDay] = {
                                    dateString:getDateString(date),
                                    date: date,
                                    eventsNum: 0,
                                    className: (date.getMonth() == $scope.date.getMonth()) ? '' : cmpName + '-outOfMonth'
                                };
                                datesHash[getDateString(date)] = daysOfCurWeak[curDay];
                                function getDateString(date) {
                                    return $filter('date')(date, 'yyyyMMdd');
                                }
                            })();
                        }
                        weaks.push(daysOfCurWeak);
                        curDate = nextDate(daysOfCurWeak[6].date);
                        if (curDate.getMonth() != $scope.date.getMonth()) {
                            break;
                        }
                    }

                    $scope.weaks = weaks;
                    loadDates();
                });
                function loadDates() {
                    var weaks = $scope.weaks;
                    loader.show();
                    var listRequest = {
                        from:weaks[0][0].dateString,
                        to:weaks[weaks.length - 1][6].dateString,
                        user: user.getLoginUser().name
                    };
                    _.each($scope.datesHash, function(item) {
                        item.eventsNum = 0;
                    });
                    calendarApi.listDatesOfMonth(listRequest).then(function(o) {
                        _.forEach(o, function(item) {
                            var dateItem = $scope.datesHash[item.dateString];
                            dateItem && (dateItem.eventsNum = item.count);
                        });
                        loader.hide();
                    });
                }

                this.clickDateItem = showDateDetail;
                function showDateDetail(dateString) {
                    var dateItem = $scope.datesHash[dateString];
                    var request = {
                        user: user.getLoginUser().name,
                        date: dateItem.dateString
                    };
                    calendarApi.getDateInfo(request).then(function(o) {
                        $scope.detail = {
                            date: dateItem.date,
                            events: o,
                            dateString: dateItem.dateString
                        };
                        console.log(o);
                        console.log($scope.detail);
                    });
                }

                this.prevMonth = function() {
                    goFirstDateOfMonth(-1);
                };
                this.nextMonth = function() {
                    goFirstDateOfMonth(1);
                };
                this.setCurrentEvent = function(event) {
                    $scope.currentEvent = event;
                };
                this.getCurrentEvent = function() {
                    return $scope.currentEvent;
                };
                this.addCurrentEvent = function(dateString) {
                    var currentEvent = this.getCurrentEvent();
                    if (currentEvent == null) {
                        return;
                    }
                    var request = {
                        id: currentEvent.id,
                        date: dateString
                    };
                    calendarApi.rescheduleEvent(request).then(function() {
                        loadDates();
                        showDateDetail(currentEvent.dateString)
                    });
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
    })
        .directive(cmpName + 'EventReceiver', function($document) {
            return {
                restrict: 'A',
                require: '^' + cmpName,
                controller: function($scope) {

                },
                link: function(scope, el, attrs, parentCtrl) {
                    el.on('mouseup', function(event) {
                        parentCtrl.addCurrentEvent(attrs.dateString)
                    });
                }
            };
        }
    );
});
