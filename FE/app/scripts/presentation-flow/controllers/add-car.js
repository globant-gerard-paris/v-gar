'use strict';

angular.module('PresentationFlow').controller('AddCarCtrl', function ($scope, RedirectSrv) {

    $scope.addCarAction = function () {
        RedirectSrv.redirectTo('/dashboard');
    };

});