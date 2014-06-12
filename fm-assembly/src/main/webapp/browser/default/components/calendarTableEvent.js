define(['app', 'config', 'underscore'], function (app, config, _) {
    var parentCmp = 'calendarTable';
    var cmpName = parentCmp + 'Event';
    app.compileProvider
        .directive(cmpName, function($document) {
            return {
                restrict: 'A',
                require: '^' + parentCmp,
                scope: true,
                controller: function($scope, $element, $attrs, $timeout, calendarApi, popupMsg) {
                    $scope.editing = false;
                    this.edit = function() {
                        $scope.editing = true;
                        $timeout(function() {
                            $element.find('textarea')[0].focus();
                        }, 1);
                    };
                    var currentDescription = $scope.event.description;

                    $scope.$watch('editing', function(val) {
                        var event = $scope.event;
                        if (!val && currentDescription != event.description) {
                            currentDescription = event.description;
                            var request = {
                                id: event._id,
                                description: event.description
                            };
                            calendarApi.updateEventDesciption(request);
                        }
                    });
                    this.startDrag = function(event) {
                        event.preventDefault();
                        $scope.ctrl.setCurrentEvent(
                            {
                                dateString: $attrs.dateString,
                                id: $attrs.eventId
                            }
                        );
                        $scope.movingel = $element.clone();
                        $scope.movingel.css({
                            position: 'fixed'
                        });
                        angular.element(document.body).append($scope.movingel);
                        $document.on('mousemove', moveFn);
                        $document.one('mouseup', function() {
                            $scope.ctrl.setCurrentEvent(null);
                            $scope.movingel && $scope.movingel.remove();
                            $scope.movingel = null;
                            $document.off('mousemove', moveFn);
                        });
                        function moveFn(event) {
                            var movingel = $scope.movingel;
                            if (movingel == null) return;
                            // plus 1 to avoid fixed position element mask the receiver element
                            var y = event.pageY + 1;
                            var x = event.pageX + 1;
                            movingel.css({
                                top: y + 'px',
                                left:  x + 'px'
                            });
                        }

                        this.edit = function(id) {
                            console.log(id)
                        };

                    };
                    this.deleteEvent = function() {
                        var event = $scope.event;
                        var request = {
                            id: event._id
                        };
                        popupMsg.confirm({
                            msg: 'sure?'
                        }).then(
                            function() {
                                return calendarApi.deleteEvent(request)
                            }
                        ).then(
                            function() {
                                $scope.ctrl.refresh();
                            }
                        );

                    };
                    this.toggleStatus = function() {
                        var event = $scope.event;
                        var request = {
                            id: event._id,
                            status: +event.status ? 0 : 1
                        };
                        calendarApi.updateStatus(request).then(function() {
                            $scope.ctrl.clickDateItem();
                        });
                    }
                },
                controllerAs: 'eventCtrl',
                link: function(scope, el, attrs) {

                },
                templateUrl: config.buildTemplatePath(cmpName)
            };
        }
    );
});
