'use strict';


angular.module('PresentationFlow').controller('CarProfileLastServicesCtrl', function ($scope, $http, $modal, config, SessionDataSrv, TrendsSrv, ApiHttpSrv, RecordSrv) {

    var mock = true;

    var familyVehicle = SessionDataSrv.getCurrentFamilyVehicle();

    $scope.model.vehicle = familyVehicle ? familyVehicle.vehicle : {};

    //TODO: remove when refactor duplicate this in record.js.
    $scope.openNewRecordForm = function () {

        var modalNewRecord = $modal.open({
            templateUrl: 'modalNewRecord.html',
            controller: 'ModalNewRecordCtrl',
            windowClass: 'vg-record-modal',
            backdropClass: 'vg-record-backdrop',
            size: 'md',
            resolve: {
                context: function () {
                    return $scope.model;
                }
            }
        });

        modalNewRecord.result.then(function (model) {
            RecordSrv.addRecord(model.recordForm).then(successAddRecord, failAddRecord);
        }, function () {
            // 'Modal dismissed at: ' + new Date()
        });

        var successAddRecord = function (response) {
            $scope.$emit('NEWLY_ADDED_RECORD', response);
        };
        var failAddRecord = function (response) {
            alert('An error has occurred, please try again.');
            console.log('ERROR: ' + response);
        };
    };

    var carsResultSuccess = function(response){
        $scope.model.services = response.data.lastServiceHistory;
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
        var userId = SessionDataSrv.getCurrentUser();
        var familyVehicleId = familyVehicle.id;
        ApiHttpSrv.createHttp('GET',config.api.hosts.BACKEND + '/car-profile/user/'+userId+'/familyvehicle/'+familyVehicleId ).then(carsResultSuccess);
        TrendsSrv.getTrend($scope.model.vehicle.make, articleResultSuccess, servicesResultFailed);
    }
});
