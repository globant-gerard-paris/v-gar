'use strict';


angular.module('PresentationFlow').controller('CarProfileLastServicesCtrl', function ($scope, $http, $modal, config, SessionDataSrv, TrendsSrv, ApiHttpSrv, RecordSrv) {

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

    var articleResultSuccess = function (response) {
        $scope.model.article = response.data || false;
    };

    var servicesResultFailed = function (response) {
        console.log('ERROR: ' + response);
    };

    $scope.$on('car-profile-data-ready', function () {
        $scope.model.services = $scope.model.data.lastServiceHistory;
    });

    TrendsSrv.getTrend($scope.model.vehicle.make, articleResultSuccess, servicesResultFailed);

});
