'use strict';

angular.module('Services').service('HomeServiceSrv', function (SessionDataSrv, ApiHttpSrv, config) {

    var getUserSessionInfo = function () {
        var token = SessionDataSrv.getCurrentToken();
        return ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/home/user/token/' + token);
    };

    return {
        getUserSessionInfo: getUserSessionInfo
    };

});