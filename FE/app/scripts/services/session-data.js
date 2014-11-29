'use strict';

angular.module('Services').service('SessionDataSrv', function () {

//    var dest = 'local',
        var currentUserKey = 'CURRENT_USER_KEY',
        currentFamilyVehicleKey = 'CURRENT_FAMILY_VEHICLE_KEY',
        currentFamilyVehiclesKey = 'CURRENT_FAMILY_VEHICLES_KEY' ;

//    var model = {
//        currentUser: null,
//        currentFamilyVehicle: null,
//        currentFamilyVehicles: null,
//        cachedVehicleUsers: null
//    };

    var isDataSaved = function () {
        return !!(getData(currentUserKey));
    };

    var saveData = function (key, value) {
        localStorage.setItem(key, JSON.stringify(value));
    };

    var getData = function (key) {
        return JSON.parse(localStorage.getItem(key));
    };

//    var reload = function () {
//        for(var propertie in model){
//            if(model.hasOwnProperty(propertie)){
//                model[propertie] = getData(propertie);
//            }
//        }
//    };
//
//    var clear = function () {
//        for(var propertie in model){
//            if(model.hasOwnProperty(propertie)){
//                window[dest+'Storage'].removeItem(propertie);
//                model[propertie] = null;
//            }
//        }
//    };

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
        return parseInt(getData(currentUserKey,10));
//        return parseInt(model.currentUser,10);
    };

    /**
     * Set the current virtual garage user.
     * @param user
     */
    var setCurrentUser = function (user) {
        saveData(currentUserKey, user);
    };

    var setCachedVehicleUsers = function (vehicles) {
    };
    var getCachedVehicleUsers = function () {
    };

    var saveCurrentFamilyVehicle = function(familyVehicle){
        saveData(currentFamilyVehicleKey,familyVehicle)
    };

    var getCurrentFamilyVehicle = function(){
        return getData(currentFamilyVehicleKey);
    };

    var saveCurrentFamilyVehicles = function(familyVehicles){
        saveData(currentFamilyVehiclesKey, familyVehicles);
    };

    var getCurrentFamilyVehicles = function(){
        return getData(currentFamilyVehiclesKey);
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
        getCurrentUser: getCurrentUser,
        setCurrentUser: setCurrentUser,
        getCurrentFamilyId: getCurrentFamilyId,
        getCurrentTangibleId: getCurrentTangibleId,
        getCurrentToken: getCurrentToken,
        getCachedVehicleUsers: getCachedVehicleUsers,
        setCachedVehicleUsers: setCachedVehicleUsers,
        saveCurrentFamilyVehicle : saveCurrentFamilyVehicle,
        getCurrentFamilyVehicle : getCurrentFamilyVehicle,
        saveCurrentFamilyVehicles: saveCurrentFamilyVehicles,
        getCurrentFamilyVehicles: getCurrentFamilyVehicles
    };

});
