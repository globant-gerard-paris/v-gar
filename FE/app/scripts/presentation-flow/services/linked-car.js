'use strict';

angular.module('PresentationFlow').service('LinkedCarSrv', function ($timeout, $http, ApiHttpSrv, config, SessionDataSrv) {

    var mock = false;

    var getLinkedCars = function (successCallback, faildCallback) {
        if(!mock){
            ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/vehicle/vehicles/'+SessionDataSrv.getCurrentUser())
                .then(successCallback, faildCallback);
        }else{
            $timeout( function(){
                $http.get('resources/mocks/linked-car.json').then(successCallback, faildCallback);
            },800);
        }
    };

    var confirmCars = function (vehicles, successCallback, faildCallback) {
        ApiHttpSrv.createHttp('POST', config.api.hosts.BACKEND + '/vehicle/vehicles/confirm/user/'+SessionDataSrv.getCurrentUser(), vehicles)
                .then(successCallback, faildCallback);
    };

    return {
        getLinkedCars: getLinkedCars,
        confirmCars: confirmCars
    };

});