define(['app', 'config', 'underscore', 'calendarTableEvent'], function (app, config, _) {
    var cmpName = 'calendarTable';
    app.compileProvider
        .directive(cmpName, function () {
        return {
            restrict: 'E',
            scope: true,
            controller: function ($scope, calendarApi, $document, loader, $filter, user, $element) {
                $scope.date = new Date();
                goFirstDateOfMonth(0);
                $scope.datesInWeak = [
                    'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'
                ];
                $scope.$watch('queryWords', function(queryWords) {
                    if (null == queryWords || queryWords.length < 1) {
                        $scope.queryResults = [];
                        return;
                    }
                    var request = {
                        queryWords: queryWords,
                        user: user.getLoginUser().name
                    };
                    calendarApi.queryEvents(request).then(function(o) {
                        $scope.queryResults = o.data;
                    });
                });
                $scope.$watch('currentEvent', function() {
                    console.log($scope.currentEvent)
                });

                function formatDateString(dateString, modify) {
                    modify = modify || {};
                    return dateString.replace(/(\d{4})(\d{2})(\d{2})/, function(p1, p2, p3, p4) {
                        return[p2,p3,(modify.date || p4)].join('-');
                    });
                }

                this.getDateObj = function(dateString) {
                    return new Date(formatDateString(dateString));
                };

                $scope.$watch('date.toDateString()', function() {
                    var weaks = [];
                    $scope.detail = null;
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
                    if ($scope.currentDateString) {
                        showDateDetail($scope.currentDateString);
                    }
                    loadDates();
                });
                this.goToToday = function() {
                    this.jumpToDate($filter('date')(new Date(), 'yyyyMMdd'));
                };
                this.jumpToDate = function(dateString) {
                    $scope.date = new Date(formatDateString(dateString, {date: '01'}));
                    this.chooseDate(dateString); // how to make sure this happen after date change complete?
                };
                $scope.$watch('currentDateString', function(dateString) {
                    showDateDetail(dateString);
                });
                this.chooseDate = function(dateString) {
                    $scope.currentDateString = dateString;
                };
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

                function showDateDetail(dateString) {
                    if (null == dateString) {
                        return;
                    }
                    var dateItem = $scope.datesHash[dateString];
                    if (dateItem == null) {
                        return;
                    }
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
                        $scope.currentDateItem && ($scope.currentDateItem.selectedClassName = '');
                        $scope.currentDateItem = dateItem;
                        dateItem.selectedClassName = 'calendarTable-dateCell-selected';
                    });
                }

                function refresh() {
                    loadDates();
                    showDateDetail($scope.currentDateItem.dateString);
                }

                this.refresh = refresh;

                this.prevMonth = function() {
                    goFirstDateOfMonth(-1);
                    $scope.currentDateString = '';
                };
                this.nextMonth = function() {
                    goFirstDateOfMonth(1);
                    $scope.currentDateString = '';
                };
                this.setCurrentEvent = function(event) {
                    $scope.currentEvent = event;
                };
                this.getCurrentEvent = function() {
                    return $scope.currentEvent;
                };

                this.rescheduleCurrentEvent = function(dateString) {
                    var currentEvent = this.getCurrentEvent();
                    if (currentEvent == null) {
                        return;
                    }
                    var request = {
                        id: currentEvent.id,
                        date: dateString
                    };
                    calendarApi.rescheduleEvent(request).then(function() {
                        refresh();
                    });
                };
                this.addNewEvent = function() {
                    var request = {
                        user: user.getLoginUser().name,
                        date: $scope.currentDateItem.dateString,
                        description: $scope.newEventDesc
                    };
                    calendarApi.addNewEvent(request).then(function() {
                        refresh();
                        $scope.newEventDesc = '';
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
                        var newDate = attrs.dateString;
                        parentCtrl.rescheduleCurrentEvent(newDate);
                    });
                    var highlightClass = 'calendarTable-dateCell-mouseOver';

                    el.on('mouseenter', function(event) {
                        el.addClass(highlightClass)
                    });
                    el.on('mouseleave', function(event) {
                        el.removeClass(highlightClass)
                    });
                }
            };
        }
    );
});
