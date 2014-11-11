'use strict';

angular.module('PresentationFlow').service('RecallsSrv', function (ApiHttpSrv, config) {

    var getRecalls = function (year, make, model, successCallback, faildCallback) {
        ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/vehicle/recalls/year/' +
                year + '/make/' + make + '/model/' + model + '/order/desc')
                .then(successCallback, faildCallback);
    };

    var getLastRecall = function (year, make, model, successCallback, faildCallback) {
        ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/vehicle/recalls/year/' +
                year + '/make/' + make + '/model/' + model + '/last')
                .then(successCallback, faildCallback);
    };

    return {
        getRecalls: getRecalls,
        getLastRecall: getLastRecall
    };

});