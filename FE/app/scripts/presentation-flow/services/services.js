/**
 * Created by diego.caro on 25/11/2014.
 */
'use strict';

angular.module('PresentationFlow').service('ServicesSrv', function (ApiHttpSrv, config) {

    var getServices = function (familyId, tangibleId, successCallback, faildCallback) {
        ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/record/family/' +
            familyId + '/vehicle/' + tangibleId + '/order/desc')
            .then(successCallback, faildCallback);
    };

    return {
        getServices: getServices
    };

});
