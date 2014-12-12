'use strict';

angular.module('PresentationFlow').service('TrendsSrv', function (ApiHttpSrv, config) {

    var getTrend = function (make, successCallback, faildCallback) {
        ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/vehicle/trends/make/' + make + '/last')
                .then(successCallback, faildCallback);
    };

    return {
        getTrend: getTrend
    };

});