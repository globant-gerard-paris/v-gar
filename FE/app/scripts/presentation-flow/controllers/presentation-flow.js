'use strict';

angular.module('PresentationFlow').controller('PresentationFlowCtrl', function ($scope, PresentationFlowSrv, RedirectSrv) {

    $scope.userTypeAction = function (action) {
        PresentationFlowSrv.setUserTypeAction(action);

        if ('FULL_USER' === action) {
            RedirectSrv.redirectTo('/dashboard');
        } else {
            RedirectSrv.redirectTo('/presentation-flow-landing');
        }
    };

});