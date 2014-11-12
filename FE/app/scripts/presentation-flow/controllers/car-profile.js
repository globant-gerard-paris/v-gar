'use strict';

angular.module('PresentationFlow').controller('CarProfileCtrl', function ($scope, RedirectSrv, RecordSrv) {

    $scope.model = {
        records: []
    };

    $scope.$on('NEWLY_ADDED_RECORD', function (/*event, dataResponse*/) {
        init();
    });
    $scope.$on('REMOVED_RECORD', function (/*event, dataResponse*/) {
        init();
    });

    var init = function () {
        RecordSrv.getRecords().then(getRecordSuccess, getRecordFail);
    };

    var getRecordSuccess = function (response) {
        $scope.model.records = response.data && response.data.length > 0 ? response.data : [];
    };
    var getRecordFail = function (response) {
        console.log('ERROR: ' + response);
        $scope.model.records = [];
    };

    /**
     * Search the store given with params and redirect to Store Locator Page.
     * @param storeZipCode
     */
    $scope.searchStore = function (storeZipCode) {
        RedirectSrv.redirectTo('/store-locator?zipcode=' + (storeZipCode || ''));
    };

    /**
     * initialize the car profile
     */
    init();

});