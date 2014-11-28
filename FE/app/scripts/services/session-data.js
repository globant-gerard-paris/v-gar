'use strict';

angular.module('Services').service('SessionDataSrv', function () {

    var dest = 'local';

    var model = {
        currentUser: null,
        cachedVehicleUsers: null
    };

    var isDataSaved = function () {
        return !!(getData('currentUser'));
    };

    var saveData = function (key, value) {
        window[dest+'Storage'].setItem(key,JSON.stringify(value));
        return value;
    };

    var getData = function (key) {
        return JSON.parse(window[dest+'Storage'].getItem(key));
    };

    var reload = function () {
        for(var propertie in model){
            if(model.hasOwnProperty(propertie)){
                model[propertie] = getData(propertie);
            }
        }
    };

    var clear = function () {
        for(var propertie in model){
            if(model.hasOwnProperty(propertie)){
                window[dest+'Storage'].removeItem(propertie);
                model[propertie] = null;
            }
        }
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
        return parseInt(model.currentUser,10);
    };

    /**
     * Set the current virtual garage user.
     * @param user
     */
    var setCurrentUser = function (user) {
        model.currentUser = saveData('currentUser',user);
    };

    var setCachedVehicleUsers = function (vehicles) {
        model.cachedVehicleUsers = saveData('cachedVehicleUsers',vehicles);
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
        isDataSave: isDataSaved,
        saveData: saveData,
        getData: getData,
        reload: reload,
        clear: clear,
        getCurrentUser: getCurrentUser,
        setCurrentUser: setCurrentUser,
        getCurrentFamilyId: getCurrentFamilyId,
        getCurrentTangibleId: getCurrentTangibleId,
        getCurrentToken: getCurrentToken,
        getCachedVehicleUsers: getCachedVehicleUsers,
        setCachedVehicleUsers: setCachedVehicleUsers
    };

});
