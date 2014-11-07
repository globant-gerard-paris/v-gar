'use strict';

angular.module('PresentationFlow').service('RecallsSrv', function (ApiHttpSrv) {

    var getRecalls = function (modelYear, make, model, successCallback, faildCallback) {

        modelYear = 0;
        make = 0;
        model = 0;

        ApiHttpSrv.createHttp('GET', 'http://127.0.0.1:8080/service/recalls/modelyear/2000/make/ITASCA/model/HORIZON')
                .then(successCallback, faildCallback);
    };

    return {
        getRecalls: getRecalls
    };

});