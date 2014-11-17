'use strict';

angular.module('PresentationFlow').controller('PresentationFlowCtrl', function ($scope, PresentationFlowSrv, RedirectSrv) {

    //TODO remove this.
    /*$scope.userTypeAction = function (action) {
        PresentationFlowSrv.setUserTypeAction(action);
        if ('FULL_USER' === action) {
            RedirectSrv.redirectTo('/dashboard');
        } else {
            RedirectSrv.redirectTo('/presentation-flow-landing');
        }
    };*/

});

//Fix carrusel ui.boostrap.
angular.module('ui.bootstrap.carousel', ['ui.bootstrap.transition'])
    .controller('CarouselController', ['$scope', '$timeout', '$transition', '$q', function        ($scope, $timeout, $transition, $q) {
    }]).directive('carousel', [function() {
        return {

        }
    }]);