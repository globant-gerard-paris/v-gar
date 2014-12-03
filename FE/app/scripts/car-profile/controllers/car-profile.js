'use strict';

angular.module('CarProfile').controller('CarProfileCtrl', function ($scope, $http, config, SessionDataSrv, ApiHttpSrv) {

    $scope.model = {};

    var carsResultSuccess = function (response) {
        $scope.model.data = response.data;
        $scope.$broadcast('car-profile-data-ready');
    };

    var userId = SessionDataSrv.getCurrentUser();
    var familyVehicle = SessionDataSrv.getCurrentFamilyVehicle();
    var familyVehicleId = familyVehicle.id;

    ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/car-profile/user/' +
            userId + '/familyvehicle/' + familyVehicleId).then(carsResultSuccess);

});
