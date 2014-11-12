'use strict';

angular.module('PresentationFlow').controller('CarProfileCtrl', function ($scope, RedirectSrv, RecordSrv, RecallsSrv, $location) {

    var modelYear = '2008',
            make = 'Ford',
            model = 'Edge';

    var params = $location.search();

    if (params.option === '2') {
        modelYear = '2010';
        make = 'Audi';
        model = 'A3';
    }

    $scope.model = {
        records: [],
        option: params.option,
        lastRecall: false,
        vehicle: {
            modelYear: modelYear,
            make: make,
            model: model
        }
    };

    $scope.searchStore = function (storeZipCode) {
        RedirectSrv.redirectTo('/store-locator?zipcode=' + (storeZipCode || ''));
    };

    $scope.viewRecalls = function (option) {
        RedirectSrv.redirectTo('/recalls?option=' + option);
    };

    $scope.$on('NEWLY_ADDED_RECORD', function (/*event, dataResponse*/) {
        init();
    });

    $scope.$on('REMOVED_RECORD', function (/*event, dataResponse*/) {
        init();
    });

    var init = function () {

        RecallsSrv.getLastRecall(modelYear, make, model, lastRecallResultSuccess, lastRecallResultFaild);

        RecordSrv.getRecords().then(getRecordSuccess, getRecordFail);

    };

    var getRecordSuccess = function (response) {
        $scope.model.records = response.data && response.data.length > 0 ? response.data : [];
    };

    var getRecordFail = function (response) {
        console.log('ERROR: ' + response);
        $scope.model.records = [];
    }

    $scope.searchStore = function (storeZipCode) {
        RedirectSrv.redirectTo('/store-locator?zipcode=' + (storeZipCode || ''));
    };

    $scope.viewRecalls = function (option) {
        RedirectSrv.redirectTo('/recalls?option=' + option);
    };


    $scope.redirectToDashboard = function () {
        RedirectSrv.redirectTo('/dashboard');
    };

    /**
     * Search the store given with params and redirect to Store Locator Page.
     * @param storeZipCode
     */
    $scope.searchStore = function (storeZipCode) {
        RedirectSrv.redirectTo('/store-locator?zipcode=' + (storeZipCode || ''));
    };

    var lastRecallResultSuccess = function (response) {
        $scope.model.lastRecall = response.data || false;
    };

    var lastRecallResultFaild = function (response) {
        console.log('ERROR: ' + response);
    };

    /**
     * initialize the car profile
     */
    init();


});