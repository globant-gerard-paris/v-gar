'use strict';

angular.module('Services').service('SessionDataSrv', function () {

    var model = {
        currentUser: null,
        cachedVehicleUsers: null
    };

    /**
     * Return the Current token from i-frame session.
     * @returns {string}
     */
    var getCurrentToken = function () {
        return window.vg.config.token;
    };

    /**
     * Return the Current userId session.
     * @returns {string}
     */
    var getCurrentUser = function () {
        return model.currentUser;
    };

    /**
     * Set the current virtual garage user.
     * @param user
     */
    var setCurrentUser = function (user) {
        model.currentUser = user;
    };

    var setCachedVehicleUsers = function (vehicles) {
        model.cachedVehicleUsers = vehicles;
    };

    var getCachedVehicleUsers = function () {
        return model.cachedVehicleUsers;
    };

    /**
     * FIXME: This should be removed.
     * @returns {string}
     */
    var getCurrentFamilyId = function () {
        return '1';
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
        setCurrentUser: setCurrentUser,
        getCurrentFamilyId: getCurrentFamilyId,
        getCurrentTangibleId: getCurrentTangibleId,
        getCurrentToken: getCurrentToken,
        getCachedVehicleUsers: getCachedVehicleUsers,
        setCachedVehicleUsers: setCachedVehicleUsers
    };

});