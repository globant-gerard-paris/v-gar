'use strict';

angular.module('PresentationFlow').service('StoreLocatorSrv', function (Geocoder, ApiHttpSrv) {

    var defaultLimitSearch = 8,
        defaultDistanceToSearch = 1000;

    /**
     *
     * Given a zipCode or Address, is normalized with Google API and then, with normalization latitude & longitude is used
     * to hit backend and return the stores near.
     *
     * @param address The address can be zipCode or just a string address.
     */
    var getStoresNearby = function (address, storeResultSuccess, storeResultFaild) {
        var geoPromise = Geocoder.geocodeAddress(address);
        geoPromise.then(
            function (result) {
                ApiHttpSrv.createHttp('GET', 'http://127.0.0.1:8080/stores/lt/' + result.lat + '/lg/' + result.lng + '?limit=' + defaultLimitSearch + '&distance=' + defaultDistanceToSearch).then(storeResultSuccess, storeResultFaild);
            },
            function (err) {
                console.log(err);
                alert('An error has occurred, please try again.');
            });
    };

    return {
        getStoreNearby: getStoresNearby
    };

});