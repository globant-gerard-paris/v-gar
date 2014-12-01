'use strict';

/**
 *  @author Jero <jeronimo.carrizo@globant.com>
 */
angular.module('PresentationFlow').service('RecordSrv', function (ApiHttpSrv, SessionDataSrv, config) {

    /**
     * Return the record service.
     * TODO: In the future this will came from a service.
     */
    var getRecordService = function () {
        return ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/record/suggestedservices');
    };

    var addRecord = function (record) {
        return ApiHttpSrv.createHttp('POST', config.api.hosts.BACKEND + '/record/familyvehicle/' + SessionDataSrv.getCurrentFamilyVehicle().id + '/record', JSON.stringify(record));
    };
    var deleteRecord = function (recordId) {
        return ApiHttpSrv.createHttp('DELETE', config.api.hosts.BACKEND + '/record/' + recordId);
    };
    var getRecords = function () {
        return ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/record/familyvehicle/' + SessionDataSrv.getCurrentFamilyVehicle().id);
    };

    return {
        getRecordService: getRecordService,
        addRecord: addRecord,
        getRecords: getRecords,
        deleteRecord: deleteRecord
    };
});