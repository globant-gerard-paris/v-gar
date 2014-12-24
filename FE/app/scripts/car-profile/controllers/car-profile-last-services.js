'use strict';

angular.module('PresentationFlow').controller('CarProfileLastServicesCtrl', function ($scope, SessionDataSrv, TrendsSrv, RedirectSrv) {

    var familyVehicle = SessionDataSrv.getCurrentFamilyVehicle();

    $scope.model.vehicle = familyVehicle ? familyVehicle.vehicle : {};

    var articleResultSuccess = function(response) {
        $scope.model.article = response.data || false;
    };

    var servicesResultFailed = function (response) {
        console.log('ERROR: ' + response);
    };

    $scope.$on('car-profile-data-ready', function(){
        $scope.model.services = $scope.model.data.lastServiceHistory;
    });

    $scope.seeAllHistory = function() {
        RedirectSrv.redirectTo('/services');
    };

    TrendsSrv.getTrend($scope.model.vehicle.make, articleResultSuccess, servicesResultFailed);

});
