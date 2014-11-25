'use strict';

angular.module('PresentationFlow').service('LinkedCarSrv', function (ApiHttpSrv, config, SessionDataSrv) {

    var getLinkedCars = function (successCallback, faildCallback) {
        ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/vehicle/vehicles/'+SessionDataSrv.getCurrentUser())
                .then(successCallback, faildCallback);
    };

    var confirmCars = function (vehicules, successCallback, faildCallback) {
        ApiHttpSrv.createHttp('POST', config.api.hosts.BACKEND + '/vehicle/vehicles/confirm/user/'+SessionDataSrv.getCurrentUser(), vehicules)
                .then(successCallback, faildCallback);
    };

    return {
        getLinkedCars: getLinkedCars,
        confirmCars: confirmCars
    };

});