'use strict';

angular.module('PresentationFlow').controller('LinkedCarCtrl', function ($scope, RedirectSrv, LinkedCarSrv, stBlurredDialog) {

    $scope.toggleCarSelect = function(car){
        if(!car){
            return;
        }
        car.isConfirmed = !car.isConfirmed;
    };

    $scope.areMineAction = function () {
        LinkedCarSrv.confirmCars($scope.model.vehicles, function () {
            RedirectSrv.redirectTo('/dashboard');
            $scope.$emit('linked-cars-updated');
        }, function (response) {
            console.log('ERROR: ' + response);
            alert('Error, please try again.');
        });
    };

    $scope.addCarAction = function () {
        RedirectSrv.redirectTo('/add-car');
    };

    $scope.model = {
        vehicles: []
    };

    $scope.totalConfirmed = 0;

    LinkedCarSrv.getLinkedCars(function (response) {
        $scope.model.vehicles = response.data || [];
    }, function (response) {
        console.log('ERROR: ' + response);
    });

    $scope.$watch('model.vehicles', function (data) {
        var count = 0;
        angular.forEach(data, function (veh) {
            count += veh.isConfirmed ? 1 : 0;
        });
        $scope.totalConfirmed = count;
    }, true);


    $scope.redirectToAddCar = function (from) {
        stBlurredDialog.open('scripts/manage-car/add-car.html',{from:from});
    };

});
