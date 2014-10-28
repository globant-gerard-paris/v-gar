/* jshint unused: false */

'use strict';

(function () {
    /* Dependencies listed here are automatically injected, and will be available for all tests */
    var autoInject = ['$rootScope', '$controller', '$location', '$routeParams', '$timeout', '$compile', '$q', 'ConfigSrv'];
    var autoModules = ['Services', 'Directives', 'Filters', 'ngRoute'];

    // injected dependencies
    var _deps = {};

    // return injected dependecy objects
    var _getDependencies = function () {
        if (window.__T__autoRegisted) {
            throw new Error('getDependencies not needed: Helpers dependencies were auto registered!');
        }
        return _deps;
    };

    // copies  injected dependencies to window, to be globally available
    var _registerDependencies = function () {
        angular.extend(window, _deps);
    };

    // creates a new scope
    var _newScope = function () {
        return _deps.$rootScope.$new();
    };

    // creates a controller, returns scope
    var _createController = function (controllerName, providers) {
        var scope;


        // if only scope passed (if it has $watch is a scope)
        if (providers) {
            if (angular.isFunction(providers.$watch)) {
                //set scope
                scope = providers;
                providers = null;
            }
        }

        if (!scope && (!providers || !providers.$scope)) {
            scope = _newScope();
        }

        //console.log(angular.extend({$scope: scope}, providers));

        //Create the controller with the new scope
        _deps.$controller(controllerName, angular.extend({$scope: scope}, providers));
        return scope;
    };

    // bootstrap all the helpers, should Be called for the others to work
    var _bootstrapTest = function (modules, injections, autoRegister) {
        window.__T__autoRegisted = autoRegister;
        /* auto include modules */
        beforeEach(function () {
            var includeArray = autoModules.concat(modules);
            _.each(includeArray, function (name) {
                module(name);
            });
        });

        // console methods to disable
//        var methods = ['log', 'debug', 'warn', 'info'];
        var methods = ['debug', 'warn', 'info'];
        // iterate
        var voidfunc = function () {
        };
        for (var i = 0; i < methods.length; i++) {
            // set to void func
            window.console[methods[i]] = voidfunc;
        }
        window.alert = function () {
        };

        /* auto inject dependencies */
        beforeEach(inject(
            function ($injector) {
                var injectArray = autoInject.concat(injections);
                // iterate inject vars
                _.each(injectArray, function (name) {
                    // make them globally available for all tests
                    _deps[name] = $injector.get(name);
                });

                if (autoRegister) {
                    // copies dependencies to window, to be globally available
                    _registerDependencies();
                }
            }
        ));

    };

    // export helpers to window
    window.testHelpers = {
        bootstrapTest: _bootstrapTest,  //bootstrap all the helpers, should Be called for the others to work
        newScope: _newScope,            //creates a new scope
        createController: _createController, // creates a controller, returns scope
        registerDependencies: _registerDependencies, // copies  injected dependencies to window, to be globally available
        getDependencies: _getDependencies // return injected dependecy objects
    };
})();