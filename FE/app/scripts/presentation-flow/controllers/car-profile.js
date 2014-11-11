'use strict';

angular.module('PresentationFlow').controller('CarProfileCtrl', function ($scope, RedirectSrv, RecallsSrv, $location) {

    $scope.searchStore = function (storeZipCode) {
        RedirectSrv.redirectTo('/store-locator?zipcode=' + (storeZipCode || ''));
    };

    $scope.viewRecalls = function (option) {
        RedirectSrv.redirectTo('/recalls?option=' + option);
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
        option: params.option,
        lastRecall: false,
        vehicle: {
            modelYear: modelYear,
            make: make,
            model: model
        }
    };

    RecallsSrv.getLastRecall(modelYear, make, model, lastRecallResultSuccess, lastRecallResultFaild);

});