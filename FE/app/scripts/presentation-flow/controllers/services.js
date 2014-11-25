'use strict';

angular.module('PresentationFlow').controller('ServicesCtrl', function ($scope, RedirectSrv, ServicesSrv) {

    $scope.model = {
        services: []
    };

    $scope.redirectToCarProfile = function (option) {
        RedirectSrv.redirectTo('/car-profile?option=' + option);
    };

    $scope.redirectToDashboard = function () {
        RedirectSrv.redirectTo('/dashboard');
    };

    var servicesResultSuccess = function (response) {
        $scope.model.services = response.data || [];
    };

    var sercicesResultFaild = function (response) {
        console.log('ERROR: ' + response);
    };

    var familyId = '8105575',
        tangibleId = '75224002';

    $scope.model = {
        services: [],
        vehicle: {
            familyId: familyId,
            tangibleId: tangibleId
        }
    };

    ServicesSrv.getServices(familyId, tangibleId, servicesResultSuccess, sercicesResultFaild);

});