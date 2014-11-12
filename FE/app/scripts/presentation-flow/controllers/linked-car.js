'use strict';

angular.module('PresentationFlow').controller('LinkedCarCtrl', function ($scope, RedirectSrv, LinkedCarSrv) {

    $scope.areMineAction = function () {
        RedirectSrv.redirectTo('/dashboard');
    };

    $scope.addCarAction = function () {
        RedirectSrv.redirectTo('/add-car');
    };

    $scope.model = {
        vehicules: []
    };

    $scope.totalConfirmed = 0;

    LinkedCarSrv.getLinkedCars(function (vehicules) {
        $scope.model.vehicules = vehicules;
    }, function (error) {
        console.log(error);
    });

    $scope.$watch('model.vehicules', function (data) {
        var count = 0;
        angular.forEach(data, function (veh) {
            count += veh.isConfirmed ? 1 : 0;
        });
        $scope.totalConfirmed = count;
    }, true);

});