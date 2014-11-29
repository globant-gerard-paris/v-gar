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
        return [
            {id: '1', desc: 'service 1'},
            {id: '2', desc: 'service 2'},
            {id: '3', desc: 'service 3'},
            {id: '4', desc: 'service 4'},
            {id: '0', desc: 'Other service'}
        ];
    };

    var addRecord = function (record) {
        return ApiHttpSrv.createHttp('POST', config.api.hosts.BACKEND + '/family/' + SessionDataSrv.getCurrentFamilyId() + '/tangible/' + SessionDataSrv.getCurrentTangibleId() + '/record', JSON.stringify(record));
    };
    var deleteRecord = function (recordId) {
        return ApiHttpSrv.createHttp('DELETE', config.api.hosts.BACKEND + '/family/' + SessionDataSrv.getCurrentFamilyId()+ '/tangible/' + SessionDataSrv.getCurrentTangibleId() + '/record/' + recordId);
    };
    var getRecords = function () {
        return ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '//record/vehicle/{familyVehicleId}/' + SessionDataSrv.getCurrentFamilyId() + '/tangible/' + SessionDataSrv.getCurrentTangibleId() + '/records');
    };

    return {
        getRecordService: getRecordService,
        addRecord: addRecord,
        getRecords: getRecords,
        deleteRecord: deleteRecord
    };
});