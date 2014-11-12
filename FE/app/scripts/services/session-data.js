'use strict';

angular.module('Services').service('SessionDataSrv', function () {

    /**
     * Return the Current userId session.
     * @returns {string}
     */
    var getCurrentUser = function () {
        return '1';//TODO: dummy user id until will be defined where we can take it.
    };
    /**
     * Return the Current familyId session.
     * @returns {string}
     */
    var getCurrentFamilyId = function () {
        return '1';//TODO: dummy family id until will be defined where we can take it.
    };
    /**
     * Return the Current TangibleId session.
     * @returns {string}
     */
    var getCurrentTangibleId = function () {
        return '1';//TODO: dummy tangible id until will be defined where we can take it.
    };

    return {
        getCurrentUser: getCurrentUser,
        getCurrentFamilyId: getCurrentFamilyId,
        getCurrentTangibleId: getCurrentTangibleId
    };

});