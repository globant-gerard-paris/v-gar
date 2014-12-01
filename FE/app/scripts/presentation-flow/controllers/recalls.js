'use strict';

angular.module('PresentationFlow').controller('RecallsCtrl', function ($scope, RedirectSrv, RecallsSrv, $location, SessionDataSrv) {

    $scope.model = {
        recalls: [],
        vehicle : SessionDataSrv.getCurrentFamilyVehicle().vehicle
    };

    $scope.redirectToCarProfile = function () {
        RedirectSrv.redirectTo('/car-profile');
    };

    $scope.redirectToDashboard = function () {
        RedirectSrv.redirectTo('/dashboard');
    };

    var recallsResultSuccess = function (response) {
        $scope.model.recalls = response.data || [];
    };

    var recallsResultFaild = function (response) {
        console.log('ERROR: ' + response);
    };

    RecallsSrv.getRecalls($scope.model.vehicle.year, $scope.model.vehicle.make, $scope.model.vehicle.model, recallsResultSuccess, recallsResultFaild);

});