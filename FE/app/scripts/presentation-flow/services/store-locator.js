'use strict';

angular.module('PresentationFlow').service('StoreLocatorSrv', function (Geocoder, ApiHttpSrv, config, SessionDataSrv, StringUtilSrv) {

    var defaultLimitSearch = 4,
        defaultDistanceToSearch = 1000,
        currentSetFavoriteStoreId,
        currentStoreNearbyLatitude,
        currentStoreNearbyLongitude;

    /**
     * Given a zipCode or Address, is normalized with Google API and then, with normalization latitude & longitude is used
     * to hit backend and return the stores near.
     *
     * @param address The address can be zipCode or just a string address.
     */
    var getStoresNearby = function (address, storeResultSuccess, storeResultFaild) {
        var geoPromise = Geocoder.geocodeAddress(address);
        geoPromise.then(
            function (result) {
                currentStoreNearbyLatitude = result.lat;
                currentStoreNearbyLongitude = result.lng;
                ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/stores/lt/' + result.lat + '/lg/' + result.lng + '?limit=' + defaultLimitSearch + '&distance=' + defaultDistanceToSearch).then(storeResultSuccess, storeResultFaild);
            },
            function (err) {
                console.log(err);
                alert('An error has occurred, please try again.');
            });
    };

    /**
     * Set a favorite store to the current user.
     * @param storeId
     * @param success
     * @param faild
     */
    var setFavoriteStore = function (storeId, success, faild) {
        currentSetFavoriteStoreId = storeId;
        var currentUserId = SessionDataSrv.getCurrentUser();
        ApiHttpSrv.createHttp('PUT', config.api.hosts.BACKEND + '/user/' + currentUserId + '/store/' + storeId).then(success, faild);
    };

    /**
     * Get the favorite store of the current user.
     * @param success
     * @param faild
     */
    var getFavoriteStore = function (success, faild) {
        var currentUserId = SessionDataSrv.getCurrentUser();
        ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/user/' + currentUserId + '/store').then(success, faild);
    };

    /**
     * Return the current favorite storeId.
     * @returns {*}
     */
    var getCurrentFavoriteStoreId = function () {
        return currentSetFavoriteStoreId;
    };

    /**
     * Return the current longitude of zip code or address of the current search locator.
     * @returns {*}
     */
    var getCurrentSearchLongitude = function () {
        return StringUtilSrv.formatCoordsNumber(currentStoreNearbyLongitude);
    };

    /**
     * Return the current latitude of zip code or address of the current search locator.
     * @returns {*}
     */
    var getCurrentSearchLatitude = function () {
        return  StringUtilSrv.formatCoordsNumber(currentStoreNearbyLatitude);
    };
    return {
        getStoreNearby: getStoresNearby,
        setFavoriteStore: setFavoriteStore,
        getFavoriteStore: getFavoriteStore,
        getCurrentFavoriteStoreId: getCurrentFavoriteStoreId,
        getCurrentSearchLatitude: getCurrentSearchLatitude,
        getCurrentSearchLongitude: getCurrentSearchLongitude
    };

});