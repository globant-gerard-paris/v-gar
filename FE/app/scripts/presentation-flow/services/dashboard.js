/**
 * Created by diego.caro on 27/11/2014.
 */
'use strict';

angular.module('PresentationFlow').service('DashboardSrv', function (ApiHttpSrv, config) {

    var getCars = function (userId, successCallback, faildCallback) {
        ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/vehicle/vehicle/confirmed/user' + userId)
            .then(successCallback, faildCallback);
    };

    return {
        getCars: getCars
    };

});
