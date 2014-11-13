'use strict';

angular.module('PresentationFlow').controller('LinkedCarCtrl', function ($scope, RedirectSrv, LinkedCarSrv) {

    $scope.areMineAction = function () {
        LinkedCarSrv.confirmCars($scope.model.vehicules, function () {
            RedirectSrv.redirectTo('/dashboard');
        }, function (response) {
            console.log('ERROR: ' + response);
            alert('Error, please try again.');
        });
    };

    $scope.addCarAction = function () {
        RedirectSrv.redirectTo('/add-car');
    };

    $scope.model = {
        vehicules: []
    };

    $scope.totalConfirmed = 0;

    LinkedCarSrv.getLinkedCars(function (response) {
        $scope.model.vehicules = response.data || [];
    }, function (response) {
        console.log('ERROR: ' + response);
    });

    $scope.$watch('model.vehicules', function (data) {
        var count = 0;
        angular.forEach(data, function (veh) {
            count += veh.isConfirmed ? 1 : 0;
        });
        $scope.totalConfirmed = count;
    }, true);

});