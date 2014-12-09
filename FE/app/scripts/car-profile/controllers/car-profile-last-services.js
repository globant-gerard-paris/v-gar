'use strict';


angular.module('PresentationFlow').controller('CarProfileLastServicesCtrl', function ($scope, $http, SessionDataSrv, TrendsSrv) {

    var mock = true;

    var vehicleData = SessionDataSrv.getCurrentFamilyVehicle();

    $scope.model.vehicle = vehicleData ? vehicleData.vehicle : {};

    var carsResultSuccess = function(response){

        $scope.model.services = response.data.lastServiceHistory || [];
    };

    var articleResultSuccess = function(response) {
        $scope.model.article = response.data || false;
    };

    var servicesResultFailed = function (response) {
        console.log('ERROR: ' + response);
    };

    if(mock){
        $http.get('resources/mocks/car-profile-data.json').then(carsResultSuccess);
        $http.get('resources/mocks/car-article.json').then(articleResultSuccess);
    }
    else{
        //DashboardSrv.getCars(userId, carsResultSuccess, carsResultFailed);
        TrendsSrv.getTrend($scope.model.vehicle.make, articleResultSuccess, servicesResultFailed);
    }
});
