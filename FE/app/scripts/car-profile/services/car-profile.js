'use strict';

angular.module('CarProfile').service('CarProfileSrv', function (SessionDataSrv, ApiHttpSrv, config) {

    var getCarProfile = function () {

        var userId = SessionDataSrv.getCurrentUser();
        var familyVehicle = SessionDataSrv.getCurrentFamilyVehicle();
        var familyVehicleId = familyVehicle.id;

        return ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/car-profile/user/' +
                userId + '/familyvehicle/' + familyVehicleId);
    };

    var updateCarName = function (name) {
        var familyVehicle = SessionDataSrv.getCurrentFamilyVehicle();
        var vehicle = {
            familyVehicleId: familyVehicle.id,
            name: name
        };
        return ApiHttpSrv.createHttp('PUT', config.api.hosts.BACKEND + '/vehicle/name/user/' +
                SessionDataSrv.getCurrentUser(), vehicle);
    };

    var blockSuggestedService = function (recommendedService, block) {
        var blocked = {
            code: block.code,
            orderNumber: recommendedService.order.orderNumber
        };
        var familyVehicle = SessionDataSrv.getCurrentFamilyVehicle();
        return ApiHttpSrv.createHttp('POST', config.api.hosts.BACKEND +
                '/car-profile/familyvehicle/' + familyVehicle.id + '/suggested', blocked);
    };

    return {
        getCarProfile: getCarProfile,
        updateCarName: updateCarName,
        blockSuggestedService: blockSuggestedService
    };

});