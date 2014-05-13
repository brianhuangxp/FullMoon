define(['angular', 'config', 'underscore', 'require', 'api'], function (angular, config, _, require) {
    'use strict';
    angular.module('myApp.common', [])
        .provider('user', function () {
            var isAdmin = false;
            var loginUser = null;
            var hasLogin = undefined;
            this.asAdmin = function () {
                isAdmin = true;
            };
            this.getLoginUser = function () {
                return {
                    name: loginUser,
                    isAdmin: isAdmin
                }
            };

            this.$get = ['$cookies' , 'userApi', '$rootScope', function ($cookies, userApi, $rootScope) {
                return {
                    hasLogin: function () {
                        return hasLogin;
                    },
                    checkLogin: function () {
                        userApi.checkLogin().then(function (o) {
                            hasLogin = o.logined;
                            loginUser = o.name;
                        });
                    },
                    isAdmin: function () {
                        return hasLogin && isAdmin;
                    },
                    login: function (user, pass) {
                        var promise = userApi.login(user, pass);
                        promise.then(function () {
                            hasLogin = true;
                            loginUser = user;
                            $cookies.user = user;
                        });
                        return promise;
                    },
                    logOut: function (remote) {
                        hasLogin = false;
                        loginUser = null;
                        if (false === remote) {
                            return;
                        }
                        userApi.logout();
                    },
                    getLoginUser: function () {
                        return {
                            name: loginUser,
                            isAdmin: isAdmin
                        };
                    }
                }
            }];
        })
        .provider('users', function () {
            var userlist = [
                {
                    name: 'aa',
                    isAdmin: true
                },
                {
                    name: 'bb',
                    isAdmin: false
                }
            ];
            this.$get = function () {
                return {
                    list: function () {
                        return userlist;
                    },
                    save: function (user) {

                    },
                    remove: function (user) {

                    }
                };
            };
        })
        .provider('loader', function () {
            this.$get = function () {
                var counter = 0;
                var dom = null;
                return {
                    show: function () {
                        counter++;
                        counter == 1 && showLoader();
                    },
                    hide: function () {
                        counter && counter--;
                        counter || hideLoader();
                    }
                };
                function hideLoader() {
                    dom && dom.remove();
                }

                function showLoader() {
                    dom = angular.element(document.createElement('div'));
                    dom.addClass('fm-loader');
                    angular.element(document.body).append(dom);
                }
            };
        })
        .provider('cssLoader', function () {
            this.$get = function ($log) {
                var loadedCss = [];

                function hasLoaded(css) {
                    return (loadedCss.indexOf(css) != -1);
                }

                return {
                    load: function (css) {
                        if (hasLoaded(css)) {
                            $log.info('css', css, 'has already loaded');
                            return;
                        }
                        var cssPath = config.componentUrl + css + '.css';
                        if (document.createStyleSheet) {
                            document.createStyleSheet(cssPath);
                        } else {
                            var link = document.createElement("link");
                            link.type = "text/css";
                            link.rel = "stylesheet";
                            link.href = cssPath;
                            document.getElementsByTagName("head")[0].appendChild(link);
                        }
                        loadedCss.push(css);
                    }
                };
            };
        })
        .provider('popupMsg', function () {
            function fn($compile, $rootScope) {
                return {
                    alert: function (config) {
                        var scope = buildScope(config);
                        scope.title = 'alert';
                        display('fm-popup-alert', scope);
                    },
                    confirm: function (config) {
                        var scope = buildScope(config);
                        scope.title = 'confirm';
                        display('fm-popup-confirm', scope);
                    }
                };

                function buildScope(config) {
                    var msg = config.msg;
                    var deferred = config.deferred;
                    var scope = $rootScope.$new();
                    scope.deferred = deferred;
                    scope.msg = msg;
                    return scope;
                }

                function display(directive, scope) {
                    angular.element(document.body).append($compile('<div ' + directive + '></div>')(scope));
                }
            }

            this.$get = ['$compile', '$rootScope', fn];
        })
        .directive('fmPopupAlert', function () {
            return {
                restrict: 'A',
                controller: function($scope, $element) {
                    this.ok = action;
                    $scope.$on('$destroy', function() {
                        $element.remove();
                    });
                    $scope.$on('fmWindowClose', function() {
                        action();
                    });
                    function action() {
                        var deferred = $scope.deferred;
                        deferred.resolve();
                        $scope.$destroy();
                    }
                },
                controllerAs: 'ctrl',
                template: '<div fm-window fm-title="{{title}}">' +    //transclude
                    ' <p ng-bind="msg"></p>' +
                    ' <div>' +
                    '   <button ng-click="ctrl.ok()" class="btn btn-primary">OK</button>' +
                    ' </div>' +
                    '</div>'
            };
        })
        .directive('fmPopupConfirm', function () {
            return {
                restrict: 'A',
                controller: function($scope, $element) {
                    this.yes = function() {
                        action(true);
                    };
                    this.no = function() {
                        action(false);
                    };
                    $scope.$on('$destroy', function() {
                        $element.remove();
                    });
                    $scope.$on('fmWindowClose', function() {
                        action(false);
                    });
                    function action(yes) {
                        var deferred = $scope.deferred;
                        yes ? deferred.resolve() : deferred.reject();
                        $scope.$destroy();
                    }
                },
                controllerAs: 'ctrl',
                template: '<div fm-window fm-title="{{title}}">' +  //transclude
                    ' <p ng-bind="msg"></p>' +
                    ' <div>' +
                    '  <button ng-click="ctrl.no()" class="btn btn-primary">No</button>' +
                    '  <button ng-click="ctrl.yes()" class="btn btn-primary">Yes</button>' +
                    ' </div>' +
                    '</div>'
            };
        })
        .directive('fmCss', function (cssLoader) {
            return {
                restrict: 'A',
                link: function (scope, el, attrs) {
                    cssLoader.load(attrs.fmCss);
                }
            };
        })
        .directive('fmOpen', function ($compile, loader, $rootScope) {
            var fmOpenScope;
            var selectedClassName = 'active';
            return {
                restrict: 'A',
                link: function (scope, el, attrs) {
                    var cmp = attrs.fmOpen;
                    var target = attrs.fmOpenAt;
                    var title = attrs.fmTitle || '';
                    var windowWidth = attrs.fmWindowWidth;
                    var containers = {
                        'window': angular.element(document.body)
                    };
                    var openAtWindow = (target == 'window');
                    var container = containers[target] || angular.element(document.body).find(target);
                    scope.$on('clearSelection', function() {
                        el.removeClass(selectedClassName);
                    });
                    el.on('click', function () {
                        loader.show();
                        require([cmp], function () {
                            loader.hide();
                            if (!openAtWindow) {
                                scope.$broadcast('beforeSelect', cmp);
                                el.addClass(selectedClassName);
                            }
                            var template = angular.element('<div fm-container></div>');
                            var cmpAttr = cmp.replace(/([A-Z])/g, '-$1');
                            if (openAtWindow) {  // fmWindow want to receive event emitted by fmCmp
                                template.attr('fm-cmp', cmpAttr);
                            } else {
                                template.append(angular.element('<div fm-cmp></div>').attr(cmpAttr, ''));
                            }
                            template.attr(openAtWindow ? 'fm-window' : 'fm-panel', '');
                            openAtWindow && template.attr('fm-width', windowWidth);
                            template.attr('fm-title', title);
                            fmOpenScope && fmOpenScope.$destroy();
                            fmOpenScope = $rootScope.$new();
                            var cmpDom = $compile(template)(fmOpenScope);
                            container.append(cmpDom);
                        });
                    });
                }
            };
        })
        .directive('fmPanel', function () {
            return {
                restrict: 'A',
                scope: true,
                controller: function($scope) {
                },
                link: function(scope, el, attrs) {
                    scope.title = attrs.fmTitle;
                    scope.$on('$destroy', function() {
                        el.remove();
                    });
                }
            };
        })
        .directive('fmWindow', function () {
            var zIndex = 10000;
            return {
                restrict: 'A',
                scope: true,
                replace: true,
                controller: ['$scope', '$element', '$attrs', '$transclude', function ($scope, $element, $attrs, $transclude) {
                    var self = this;
                    var fmWidth = $attrs.fmWidth || '400px';
                    increaseZIndex();
                    $scope.winStyle = {
                        width: fmWidth,
                        'z-index': zIndex
                    };
                    $scope.maskStyle = {
                        'z-index': zIndex - 1
                    };
                    $scope.$on('$destroy', function() {
                        decreaseZIndex();
                        $element.remove();
                    });
                    $scope.$on('close', function(e, promise) {
                        self.close(promise);
                    });
                    $scope.$on('safeClose', function(e, fn) {
                        self.safeClose = fn;
                    });
                    this.close = function (promise) {
                        _.isObject(promise) ? promise.then(closeFn) : closeFn();
                        function closeFn() {
                            $scope.$emit('fmWindowClose');
                            $scope.$destroy();
                        }
                    };
                    this.safeClose = this.close;
                }],
                link: function (scope, el, attrs) {
                    scope.fmTitle = attrs.fmTitle;   // wait for interpolate
                },
                transclude: true,
                controllerAs: 'ctrl',
                template: function (el, attrs) {
                    return '<div>' +
                        ' <div class="fm-ui-popup-container panel panel-primary" ng-style="winStyle">' +
                        '  <div class="panel-heading">' +
                        '   <span class="panel-title" ng-bind="fmTitle"></span>' +
                        '   <button type="button" class="close" aria-hidden="true" ng-click="ctrl.safeClose()">&times;</button>' +
                        '  </div>' +
                        '  <div class="panel-body">' +
                        '   <div ' + attrs.fmCmp + '></div>' +
                        '   <div ng-transclude></div>' +
                        '  </div>' +
                        ' </div>' +
                        ' <div ng-style="maskStyle" class="fm-ui-popup-maskLayer"></div>' +
                        '</div>';
                }
            };
            function increaseZIndex() {
                zIndex = zIndex + 2;
            }

            function decreaseZIndex() {
                zIndex = zIndex - 2;
            }
        })
        .directive('fmLeftMenuContainer', function () {
            return {
                restrict: 'A',
                scope: true,
                controller: function($scope) {
                    $scope.$on('beforeSelect', function(e, selectedItem) {
                        $scope.$broadcast('clearSelection');
                    });
                }
            };
        })
        .directive('fmHash', function (user, $location) {
            return {
                restrict: 'A',
                link: function (scope, el, attrs) {
                    var hash = attrs.fmHash;
                    el.on('click', function() {
                        $location.hash(hash);
                    });
                    hash == $location.hash() && el.triggerHandler('click');
                }
            };
        })
        .directive('fmTitle', function () {
            return {
                restrict: 'A',
                link: function (scope, el, attrs) {
                    el.on('click', function() {
                        scope.title = attrs.fmTitle;
                    });
                }
            };
        })
        .directive('fmFlow', function ($compile) {
            return {
                restrict: 'A',
                controller: function($scope, $element, $attrs, $transclude) {
                    var firstStepCmp = null;
                    /**
                     *  stepsConfig looks like:
                     *  {
                     *      $currentStep: stepName
                     *      stepName : {
                     *          cmp
                     *          el
                     *          scope
                     *          rules: {
                     *              next/back: {
                     *                  name
                     *                  restore: true/false
                     *              }
                     *          }
                     *      }
                     *   }
                     **/
                    var stepsConfig = {$currentStep: ''};
                    this.setFirstStep = function(cmp) {
                        firstStepCmp = cmp;
                    };
                    this.getFirstStep = function() {
                        return firstStepCmp;
                    };
                    this.setStep = function(stepName, config) {
                        _.each(['next', 'back'], function(keyName) {
                            parseConfig(keyName);
                        });
                        stepsConfig[stepName] = config;
                        function parseConfig(keyName) {
                            var ruleConfig = config.rules[keyName];
                            if (_.isString(ruleConfig)) {
                                config.rules[keyName] = parseAction(ruleConfig);
                            }
                        }
                    };
                    $scope.$on('next', onSwitch);
                    $scope.$on('back', onSwitch);
                    $scope.$on('cancel', onSwitch);
                    $scope.$on('done', onSwitch);
                    this.getStepsConfig = function() {
                        return stepsConfig;
                    };
                    this.parseAction = parseAction;

                    function onSwitch(e, params, ruleConfig) {
                        var action = e.name;
                        var currentStepName = params.fmStepName;
                        var actionConfig = parseAction(ruleConfig) || params.actionConfig || stepsConfig[currentStepName].rules[action];
                        if (actionConfig == null) {
                            throw Error("rule hasn't configured");
                        }
                        var handler = actionConfig.name;
                        var restore = actionConfig.restore;
                        (restore == null) && (restore = (action == 'back'));
                        showStep({
                            stepsConfig: stepsConfig,
                            scope: $scope,
                            stepName :_.isFunction(handler) ? handler() : handler,
                            el: $element,
                            restore: _.isFunction(restore) ? restore() : restore
                        });
                    }
                },
                require: 'fmFlow',
                link: function (scope, el, attrs, ctrl) {
                    showStep({
                        stepsConfig: ctrl.getStepsConfig(),
                        scope: scope,
                        stepName :ctrl.getFirstStep(),
                        el: el
                    });
                }
            };
            function showStep(config) {

                var stepsConfig = config.stepsConfig;
                var stepName = config.stepName;
                var scope = config.scope;
                var el = config.el;
                var restore = config.restore;
                var currentStepConfig = stepsConfig[stepsConfig.$currentStep] || {};
                var cmp = stepsConfig[stepName].cmp;
                var currentEl = currentStepConfig.el;
                currentEl && currentEl.css({'display': 'none'});

                require([cmp], function() {
                    var oldScope = stepsConfig[stepName].scope;
                    var oldEl = stepsConfig[stepName].el;
                    if (!restore || oldScope == null) {    // create new el and scope
                        oldScope && oldScope.$destroy();
                        oldEl && oldEl.remove();
                        var stepScope = scope.$new();
                        var stepEl = $compile('<div ' + cmp + ' fm-step-name="' + stepName + '"></div>')(stepScope);
                        stepsConfig[stepName].el = stepEl;
                        stepsConfig[stepName].scope = stepScope;
                        el.append(stepEl);
                    } else {
                        oldEl.css({'display': 'block'});
                    }
                    stepsConfig.$currentStep = stepName;
                });
            }

            function parseAction(actionStr) {
                if (actionStr == null) return null;
                var restore;
                /^restore:/.test(actionStr) && (restore = true);
                /^new:/.test(actionStr) && (restore = false);
                return {
                    name: actionStr.replace(/(restore|new):/, ''),
                    restore:  restore
                };
            }
        })
        .directive('fmFlowStep', function () {
            return {
                restrict: 'A',
                require: '^fmFlow',
                link: function (scope, el, attrs, ctrl) {
                    var cmp = attrs.fmFlowStep;
                    var stepName = attrs.fmFlowStep || attrs.fmFlowStepName;
                    var isFirstStep = attrs.hasOwnProperty('fmFirstStep');
                    isFirstStep && ctrl.setFirstStep(stepName);
                    attrs.$set('fmStepName', stepName);
                    ctrl.setStep(stepName, {
                        cmp: cmp,
                        rules: {
                            next: attrs.fmFlowNext,
                            back: attrs.fmFlowBack
                        }
                    })
                }
            };

        })
        .directive('requireAdmin', function (user) {
            return {
                restrict: 'A',
                link: function (scope, el) {
                    if (user.isAdmin()) {
                        el.removeClass('ng-hide');
                    } else {
                        el.addClass('ng-hide');
                    }
                }
            };
        })
        .factory('ajaxService', function ($http, $q, $rootScope,loader) {
            // todo add client cache.
            return{
                send: function (option) {
                    var deferred = $q.defer();
                    loader.show();
                    $http({
                            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                            url: option.url,
                            method: "post",
                            data: option.requestData,
                            transformRequest: function(obj) {
                                var str = [];
                                for(var p in obj)
                                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                                return str.join("&");
                            }
                        }
                    ).success(function (data, status, headers, config) {
                            if (data.status.statusCode) {
                                deferred.resolve(data, status, headers, config);
                            } else {
                                deferred.reject(data, status, headers, config);
                            }
                            loader.hide();
                        }).error(function (data, status, headers, config) {
                            alert(status);
                            deferred.reject(data, status, headers, config);
                            loader.hide();
                        }
                    );
                    return deferred.promise;
                }
            }

        })
});
