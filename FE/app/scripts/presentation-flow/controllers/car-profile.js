'use strict';

angular.module('PresentationFlow').controller('CarProfileCtrl', function ($scope, RedirectSrv, RecallsSrv) {

    $scope.searchStore = function (storeZipCode) {
        RedirectSrv.redirectTo('/store-locator?zipcode=' + (storeZipCode || ''));
    };

    $scope.viewRecalls = function () {
        RedirectSrv.redirectTo('/recalls');
    };

    $scope.model = {
        lastRecall: false
    };

    var lastRecallResultSuccess = function (response) {
        $scope.model.lastRecall = response.data || false;
    };

    var lastRecallResultFaild = function (response) {
        console.log('ERROR: ' + response);
    };

    var modelYear = '2000',
            make = 'ITASCA',
            model = 'HORIZON';

    RecallsSrv.getLastRecall(modelYear, make, model, lastRecallResultSuccess, lastRecallResultFaild);

});