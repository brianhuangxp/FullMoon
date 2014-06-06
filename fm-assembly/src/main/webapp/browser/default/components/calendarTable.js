define(['app', 'config', 'underscore'], function (app, config, _) {
    var cmpName = 'calendarTable';
    app.compileProvider
        .directive(cmpName, function () {
        return {
            restrict: 'E',
            scope: true,
            controller: function ($scope, calendarApi, $document) {
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
                                    dateString: date.toDateString(),
                                    date: date,
                                    eventsNum: 0,
                                    className: (date.getMonth() == $scope.date.getMonth()) ? '' : cmpName + '-outOfMonth'
                                };
                                datesHash[date.toDateString()] = daysOfCurWeak[curDay];
                            })();
                        }
                        weaks.push(daysOfCurWeak);
                        curDate = nextDate(daysOfCurWeak[6].date);
                        if (curDate.getMonth() != $scope.date.getMonth()) {
                            break;
                        }
                    }

                    $scope.weaks = weaks;
                    calendarApi.listDatesOfMonth(weaks).then(function(o) {
                        _.forEach(o, function(item) {
                            var dateItem = datesHash[item.date.toDateString()];
                            dateItem && (dateItem.eventsNum = item.eventsNum);
                        });
                    });
                });

                this.clickDateItem = function(dateItem) {
                    calendarApi.getDateInfo(dateItem.date).then(function(o) {
                        $scope.detail = {
                            date: dateItem.date,
                            events: o.events,
                            dateString: dateItem.dateString
                        };
                        console.log($scope.detail);
                    });
                };
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
                    var dateItem = $scope.datesHash[dateString];
                    if ($scope.currentEvent && $scope.currentEvent.dateString != dateString) {
                        dateItem.eventsNum++;
                        $scope.$digest();
                    }
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
        .directive(cmpName + 'Event', function($document) {
            return {
                restrict: 'A',
                require: '^' + cmpName,
                scope: true,
                controller: function($scope) {
                    console.log($scope)
                },
                controllerAs: 'ctrl',
                link: function(scope, el, attrs, parentCtrl) {
                    el.on('mousedown', function(event) {
                        event.preventDefault();
                        parentCtrl.setCurrentEvent(
                            {
                                dateString: attrs.dateString,
                                id:attrs.eventId
                            }
                        );
                        scope.movingel = el.clone();
                        scope.movingel.css({
                            position: 'fixed'
                        });
                        angular.element(document.body).append(scope.movingel);
                        $document.on('mousemove', moveFn);
                        $document.one('mouseup', function() {
                            parentCtrl.setCurrentEvent(null);
                            scope.movingel && scope.movingel.remove();
                            scope.movingel = null;
                            $document.off('mousemove', moveFn);
                        });
                        function moveFn(event) {
                            var movingel = scope.movingel;
                            if (movingel == null) return;
                            // plus 1 to avoid fixed position element mask the receiver element
                            var y = event.pageY + 1;
                            var x = event.pageX + 1;
                            movingel.css({
                                top: y + 'px',
                                left:  x + 'px'
                            });
                        }
                    });
                }
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
