'use strict';

angular.module('PresentationFlow').service('RecallsSrv', function (ApiHttpSrv, config) {

    var getRecalls = function (year, make, model, successCallback, faildCallback) {
        
        config.api.hosts.BACKEND = 'http://127.0.0.1:8080/service';
        
        ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/vehicle/recalls/year/' +
                year + '/make/' + make + '/model/' + model + '/order/desc')
                .then(successCallback, faildCallback);
    };

    return {
        getRecalls: getRecalls
    };

});