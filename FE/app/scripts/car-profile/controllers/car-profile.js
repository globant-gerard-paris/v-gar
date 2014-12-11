'use strict';

angular.module('CarProfile').controller('CarProfileCtrl', function ($scope, $modal, config, SessionDataSrv, ApiHttpSrv, RecordSrv) {

    $scope.model = {};

    var carsResultSuccess = function (response) {
        $scope.model.data = response.data;
        $scope.$broadcast('car-profile-data-ready');
    };

    //Set POPUP.
    $scope.model = {
        dateRecordFormat: 'MM/dd/yyyy',
        servicesPopup: null
    };

    var successGetServices = function (response) {
        $scope.model.servicesPopup = response.data;
    };

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

    var userId = SessionDataSrv.getCurrentUser();
    var familyVehicle = SessionDataSrv.getCurrentFamilyVehicle();
    var familyVehicleId = familyVehicle.id;
    RecordSrv.getRecordService().then(successGetServices);
    ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/car-profile/user/' +
            userId + '/familyvehicle/' + familyVehicleId).then(carsResultSuccess);

});
