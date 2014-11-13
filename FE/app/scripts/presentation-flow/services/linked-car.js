'use strict';

angular.module('PresentationFlow').service('LinkedCarSrv', function (ApiHttpSrv, config) {

    var getLinkedCars = function (successCallback, faildCallback) {
        ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/vehicle/vehicles/1')
                .then(successCallback, faildCallback);
    };

    var confirmCars = function (vehicules, successCallback, faildCallback) {
        ApiHttpSrv.createHttp('POST', config.api.hosts.BACKEND + '/vehicle/vehicles/confirm/user/1', vehicules)
                .then(successCallback, faildCallback);
    };

    return {
        getLinkedCars: getLinkedCars,
        confirmCars: confirmCars
    };

});