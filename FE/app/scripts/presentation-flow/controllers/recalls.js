'use strict';

angular.module('PresentationFlow').controller('RecallsCtrl', function ($scope, RedirectSrv, RecallsSrv, $location) {

    $scope.model = {
        recalls: []
    };

    $scope.redirectToCarProfile = function (option) {
        RedirectSrv.redirectTo('/car-profile?option=' + option);
    };

    $scope.redirectToDashboard = function () {
        RedirectSrv.redirectTo('/dashboard');
    };

    var recallsResultSuccess = function (response) {
        $scope.model.recalls = response.data || [];
    };

    var recallsResultFaild = function (response) {
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
        recalls: [],
        vehicle: {
            modelYear: modelYear,
            make: make,
            model: model
        }
    };

    RecallsSrv.getRecalls(modelYear, make, model, recallsResultSuccess, recallsResultFaild);

});