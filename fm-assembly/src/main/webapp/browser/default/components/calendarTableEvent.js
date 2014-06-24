define(['app', 'config', 'underscore', 'recorderjs'], function (app, config, _) {
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
                        $scope.movingel = angular.element('<div>' + $scope.event.description + '</div>');
                        $scope.movingel.css({
                            position: 'fixed',
                            width: '150px'
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
                        event.status = +event.status ? 0 : 1;
                        var request = {
                            id: event._id,
                            status: event.status
                        };
                        calendarApi.updateStatus(request).then(function() {
                        });
                    };

                    var audioRecorder;
                    var timeout;
                    var microphone;
                    var inputPoint;
                    var audioContext;
                    var microphoneStream;

                    this.toggleRecord = function() {
                        if (!$scope.recording) {
                            if ($scope.recordStatus.recording) {
                                alert('occupied');
                                return;
                            }
                            startRecording();
                        } else {
                            stopRecording();
                        }
                    };
                    function startRecording() {
                        $scope.recording = true;
                        $scope.recordStatus.recording = true;
                        navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia;
                        navigator.getUserMedia({audio:true}, gotStream, function(e) {
                            console.log(e)
                        });
                    }

                    function gotStream(stream) {
                        window.AudioContext = window.AudioContext || window.webkitAudioContext;
                        audioContext = config.audioContext;
                        microphoneStream = stream;
                        microphone = audioContext.createMediaStreamSource(stream);
                        inputPoint = audioContext.createGain();
                        audioRecorder = new Recorder(inputPoint);
                        microphone.connect(inputPoint);
                        inputPoint.connect(audioContext.destination);
                        audioRecorder.record();
                        timeout && clearTimeout(timeout);
                        timeout = setTimeout(stopRecording, 10000);
                    }

                    function stopRecording() {
                        audioContext = null;
                        timeout && clearTimeout(timeout);
                        if (audioRecorder == null) return;
                        microphoneStream && microphoneStream.stop();
                        microphone && microphone.disconnect();
                        inputPoint && inputPoint.disconnect();
                        audioRecorder.stop();
                        audioRecorder.exportWAV(function(blob) {
                            calendarApi.uploadEventAudio({
                                blob: blob,
                                eventId: $scope.event._id
                            }).then(function(o) {
                                    $element.find('audio')[0].src = window.URL.createObjectURL(blob);
                                    $scope.event.path = o.data.path;
                                    audioRecorder.stop();
                                    $scope.recording = false;
                                    $scope.recordStatus.recording = false;
                                }
                            );
                        });
                    }
                },
                controllerAs: 'eventCtrl',
                link: function(scope, el, attrs) {
                    el.on('mouseover', function() {
                        el.toggleClass('calendarTable-event-mouseover');
                        var src = el.find('audio')[0].src;
                        if (src == "") {
                            el.find('audio')[0].src = '/event/audio/' + scope.event.path;
                        }
                    });
                    el.on('mouseout', function() {
                        el.toggleClass('calendarTable-event-mouseover');
                    })
                },
                templateUrl: config.buildTemplatePath(cmpName)
            };
        }
    );
});
