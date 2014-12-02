/**
 * Created by diego.caro on 25/11/2014.
 */
'use strict';

angular.module('PresentationFlow').service('ServicesSrv', function (ApiHttpSrv, config) {

    var getServices = function (familyVehicleId, successCallback, faildCallback) {
        ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/record/familyvehicle/' + familyVehicleId)
            .then(successCallback, faildCallback);
    };

    return {
        getServices: getServices
    };

});
