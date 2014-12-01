'use strict';

angular.module('PresentationFlow').controller('CarProfileCtrl', function ($scope, RedirectSrv, RecordSrv, RecallsSrv, TrendsSrv, SessionDataSrv) {


    $scope.model = {
        records: [],
        lastRecall: false,
        trend: false,
        vehicle: SessionDataSrv.getCurrentFamilyVehicle().vehicle,
        vehicles: SessionDataSrv.getCurrentFamilyVehicles(),
        mileage: SessionDataSrv.getCurrentFamilyVehicle().mileage
    };

    /**
     * Search the store given with params and redirect to Store Locator Page.
     * @param storeZipCode
     */
    $scope.searchStore = function (storeZipCode) {
        RedirectSrv.redirectTo('/store-locator?zipcode=' + (storeZipCode || ''));
    };

    $scope.viewRecalls = function () {
        RedirectSrv.redirectTo('/recalls');
    };

    $scope.getCurrentVehicles = function () {
        return SessionDataSrv.getCurrentFamilyVehicles();
    };
    $scope.getCurrentVehicle = function () {
        return SessionDataSrv.getCurrentFamilyVehicle();
    };

    $scope.$on('NEWLY_ADDED_RECORD', function (/*event, dataResponse*/) {
        init();
    });

    $scope.$on('REMOVED_RECORD', function (/*event, dataResponse*/) {
        init();
    });

    var init = function () {
        $scope.model.vehicle = SessionDataSrv.getCurrentFamilyVehicle().vehicle;
        $scope.model.vehicles = SessionDataSrv.getCurrentFamilyVehicles();
        $scope.model.mileage = SessionDataSrv.getCurrentFamilyVehicle().mileage;

        RecallsSrv.getLastRecall($scope.model.vehicle.year, $scope.model.vehicle.make, $scope.model.vehicle.model, lastRecallResultSuccess, lastRecallResultFaild);
        TrendsSrv.getTrend($scope.model.vehicle.make, trendResultSuccess, trendResultFaild);
        RecordSrv.getRecords().then(getRecordSuccess, getRecordFail);
    };

    var getRecordSuccess = function (response) {
        $scope.model.records = response.data && response.data.length > 0 ? response.data : [];
    };

    var getRecordFail = function (response) {
        console.log('ERROR: ' + response);
        $scope.model.records = [];
    };

    $scope.redirectToDashboard = function () {
        RedirectSrv.redirectTo('/dashboard');
    };

    var lastRecallResultSuccess = function (response) {
        $scope.model.lastRecall = response.data || false;
    };

    var lastRecallResultFaild = function (response) {
        console.log('ERROR: ' + response);
    };

    var trendResultSuccess = function (response) {
        $scope.model.trend = response.data || false;
    };

    var trendResultFaild = function (response) {
        console.log('ERROR: ' + response);
    };

    /**
     * initialize the car profile
     */
    init();


});