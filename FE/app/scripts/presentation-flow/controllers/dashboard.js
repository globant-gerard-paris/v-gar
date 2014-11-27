'use strict';

angular.module('PresentationFlow').controller('DashboardCtrl', function ($scope, RedirectSrv, DashboardSrv) {

    $scope.redirectToCarProfile = function () {
        RedirectSrv.redirectTo('/car-profile');
    };

    $scope.redirectToAddCar = function () {
        RedirectSrv.redirectTo('/add-car');
    };

    $scope.getToCarProfile = function (option) {
        RedirectSrv.redirectTo('/car-profile?option=' + option);
    };

    $scope.model = {
        cars: []
    };

    var carsResultSuccess = function (response) {
        $scope.model.cars = response.data || [];
    };

    var carsResultFailed = function (response) {
        console.log('ERROR: ' + response);
    };

    var userId = '5';

    $scope.model = {
        cars: [],
        vehicle: {
            userId: userId
        }
    };

    DashboardSrv.getCars(userId, carsResultSuccess, carsResultFailed);
});