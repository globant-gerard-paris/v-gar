'use strict';

angular.module('PresentationFlow').service('PresentationFlowSrv', function () {

    var userTypeAction = '';

    var getUserTypeAction = function () {
        return userTypeAction;
    };

    var setUserTypeAction = function (action) {
        userTypeAction = action;
    };

    return {
        getUserTypeAction: getUserTypeAction,
        setUserTypeAction: setUserTypeAction
    };
});