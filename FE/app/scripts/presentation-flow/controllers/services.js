'use strict';

angular.module('PresentationFlow').controller('ServicesCtrl', function ($scope, RedirectSrv, ServicesSrv, $http) {

    $scope.model = {
        services: []
    };

    var mock = true;


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



    if(mock){
        $http.get('resources/mocks/services.json').then(servicesResultSuccess, sercicesResultFaild);
    }
    else{
        ServicesSrv.getServices(familyId, tangibleId, servicesResultSuccess, sercicesResultFaild);
    }

});