'use strict';

angular.module('CarProfile').controller('CarProfileCtrl', function ($scope, $modal, CarProfileSrv, RecordSrv, RedirectSrv, $rootScope, $location, $anchorScroll) {

    $scope.model = {
        recallsOrRecommended: true,
        carNameEditMode: true,
        carName: null
    };

    var carsResultSuccess = function (response) {
        $scope.model.data = response.data;
        $scope.model.carName = $scope.model.data.vehicle.name;
        $scope.model.hasRecalls = response.data.recallsInformation &&
                response.data.recallsInformation.totalRecalls > 0;
        $scope.model.hasRecommended = response.data.recommendedService &&
                response.data.recommendedService.serviceRecordItems &&
                response.data.recommendedService.serviceRecordItems.length > 0;
        $scope.model.recallsOrRecommended =
                $scope.model.hasRecalls || $scope.model.hasRecommended;
        $scope.model.hasServices = response.data.lastServiceHistory &&
                response.data.lastServiceHistory.length > 0;
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

    $scope.openEditName = function () {
        $scope.model.carNameEditMode = true;
    };

    $scope.closeEditName = function () {
        $scope.model.carName = $scope.model.data.vehicle.name;
        $scope.model.carNameEditMode = false;
    };

    $scope.deleteName = function () {
        $scope.model.carName = null;
        $scope.updateName();
    };

    $scope.updateName = function () {
        $scope.model.data.vehicle.name = $scope.model.carName;
        CarProfileSrv.updateCarName($scope.model.data.vehicle.name).then($scope.closeEditName);
    };

    $scope.$on('BLOCK_SUGGESTED_SERVICE', function () {
        $scope.loadProfileData();
    });

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
            $scope.loadProfileData();
            $scope.$emit('NEWLY_ADDED_RECORD', response);
        };
        var failAddRecord = function (response) {
            alert('An error has occurred, please try again.');
            console.log('ERROR: ' + response);
        };
    };

    $scope.loadProfileData = function () {
        RecordSrv.getRecordService().then(successGetServices);
        CarProfileSrv.getCarProfile().then(carsResultSuccess);
    };

    $scope.redirectToRecalls = function () {
        RedirectSrv.redirectTo('/recalls');
    };


    var successBlock = function () {
        $rootScope.$broadcast('BLOCK_SUGGESTED_SERVICE');
    };

    $scope.blockSuggested = function (suggested) {
        CarProfileSrv.blockSuggestedService($scope.model.data.recommendedService, suggested).then(successBlock);
    };

    $scope.goToRecommended = function (id) {
        $location.hash(id);
        $anchorScroll();
    };

    //load initial data
    $scope.loadProfileData();

});
