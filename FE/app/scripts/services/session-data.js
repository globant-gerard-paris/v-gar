'use strict';
/*jshint -W033*/
/*jshint -W098*/
/*jshint -W116*/
angular.module('Services').service('SessionDataSrv', function () {

    var currentUserKey = 'CURRENT_USER_KEY',
        currentUserNameKey = 'CURRENT_USER_NAME_KEY',
        currentFamilyVehicleKey = 'CURRENT_FAMILY_VEHICLE_KEY',
        currentSywMembernumberKey = 'CURRENT_SYW_MEMBERNUMBER_KEY',
        currentFavoriteStoreKey = 'CURRENT_FAVORITE_STORE_KEY',
        currentFamilyVehiclesKey = 'CURRENT_FAMILY_VEHICLES_KEY';

    var isDataSaved = function () {
        return !!(getData(currentUserKey));
    };

    var saveData = function (key, value) {
        localStorage.setItem(key, JSON.stringify(value));
    };

    var getData = function (key) {
        if(localStorage.getItem(key) && localStorage.getItem(key) !== 'undefined'){
            return JSON.parse(localStorage.getItem(key));
        }else{
            return '';
        }
    };

    var saveSywMemberNumber = function(value){
        saveData(currentSywMembernumberKey, JSON.stringify(value));
    };

    var getSywMemberNumber = function(){
        return getData(currentSywMembernumberKey);
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
        return parseInt(getData(currentUserKey),10);
    };

    /**
     * Set the current virtual garage user.
     * @param user
     */
    var setCurrentUser = function (user) {
        saveData(currentUserKey, user);
    };
    /**
     * Return the Current favorite store.
     * @returns {string}
     */
    var getCurrentFavoriteStore = function () {
        return parseInt(getData(currentFavoriteStoreKey),10);
    };

    /**
     * Set the current favorite store.
     * @param user
     */
    var saveCurrentFavoriteStore = function (user) {
        saveData(currentFavoriteStoreKey, user);
    };

    /**
     * Return the Current Name session.
     * @returns {string}
     */
    var getCurrentUserName = function () {
        return getData(currentUserNameKey);
    };

    /**
     * Set the current virtual garage user Name.
     * @param user
     */
    var setCurrentUserName = function (user) {
        saveData(currentUserNameKey, user);
    };

    var setCachedVehicleUsers = function (vehicles) {
        console.log(vehicles);
    };

    var saveCurrentFamilyVehicle = function (familyVehicle) {
        saveData(currentFamilyVehicleKey, familyVehicle);
    };

    var getCurrentFamilyVehicle = function () {
        return getData(currentFamilyVehicleKey);
    };

    var saveCurrentFamilyVehicles = function (familyVehicles) {
        saveData(currentFamilyVehiclesKey, familyVehicles);
    };

    var getCurrentFamilyVehicles = function () {
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

    /**
     * Detecting a mobile browser.
     * @returns {boolean}
     */
    var isMobileDevice = function() {
        var check = false;
        (function(a,b){if(/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4)))check = true})(navigator.userAgent||navigator.vendor||window.opera);
        return check;
    };

    return {
        isDataSave: isDataSaved,
        saveData: saveData,
        getData: getData,
        getCurrentUser: getCurrentUser,
        getCurrentUserName: getCurrentUserName,
        setCurrentUser: setCurrentUser,
        setCurrentUserName: setCurrentUserName,
        getCurrentFamilyId: getCurrentFamilyId,
        getCurrentTangibleId: getCurrentTangibleId,
        getCurrentToken: getCurrentToken,
        setCachedVehicleUsers: setCachedVehicleUsers,
        saveCurrentFamilyVehicle: saveCurrentFamilyVehicle,
        getCurrentFamilyVehicle: getCurrentFamilyVehicle,
        saveCurrentFamilyVehicles: saveCurrentFamilyVehicles,
        getCurrentFamilyVehicles: getCurrentFamilyVehicles,
        isMobileDevice: isMobileDevice,
        saveSywMemberNumber: saveSywMemberNumber,
        getSywMemberNumber: getSywMemberNumber,
        getCurrentFavoriteStore: getCurrentFavoriteStore,
        saveCurrentFavoriteStore: saveCurrentFavoriteStore
    };

});
