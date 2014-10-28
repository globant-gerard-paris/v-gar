'use strict';

angular.module('PresentationFlow').controller('DashboardCtrl', function ($scope, RedirectSrv) {

    $scope.redirectToCarProfile = function () {
        RedirectSrv.redirectTo('/car-profile');
    };

    $scope.redirectToAddCar = function () {
        RedirectSrv.redirectTo('/add-car');
    };

});