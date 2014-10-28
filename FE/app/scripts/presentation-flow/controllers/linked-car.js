'use strict';

angular.module('PresentationFlow').controller('LinkedCarCtrl', function ($scope, RedirectSrv) {

    $scope.areMineAction = function () {
        RedirectSrv.redirectTo('/dashboard');
    };

    $scope.addCarAction = function () {
        RedirectSrv.redirectTo('/add-car');
    };

});