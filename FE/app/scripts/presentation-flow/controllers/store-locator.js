'use strict';

/**
 *  @author Jero <jeronimo.carrizo@globant.com>
 */
angular.module('PresentationFlow').controller('StoreLocatorCtrl', function ($scope, StoreLocatorSrv, $location, StringUtilSrv, Geocoder, $timeout) {
    //var mySwiper;

    $scope.model = {
        stores: [],
        address: null,
        oldAddress: null,
        isCollapsed: true,
        myStore: null,
        myStoreId: null,
        searching: false,
        count: 3
    };

    $scope.$on('SET_FAVORITE_STORE_SUCCESS', function (/*event, dataResponse*/) {
        initialize();
    });

    $scope.$on('NG_REPEAT_FINISHED', function(/*ngRepeatFinishedEvent*/) {
//        mySwiper = new Swiper('.swiper-container',{
////                pagination: '.pagination',
//            paginationClickable: true,
//            centeredSlides: true,
//            slidesPerView: 'auto'
//        });
    });

    /**
     * This method initialize the controller.
     */
    var initialize = function () {
        var params = $location.search();
        if ((params && params.zipcode)) {
            $scope.model.address = params.zipcode;
            $scope.model.oldAddress = params.zipcode;

            //TODO implement promises
            StoreLocatorSrv.getStoreNearby($scope.model.address, getStoreNearbySuccess, getStoreNearbyFail);
        } else {
            StoreLocatorSrv.getFavoriteStore(getFavoriteStoreSuccess, getFavoriteStoreFail);
        }
    };

    var getStoreNearbySuccess = function (response) {
        $scope.model.searching = false;
        $scope.model.stores = response.data[0] || [];
        fixLatitudeLongitudeStores($scope.model.stores);
        StoreLocatorSrv.getFavoriteStore(getFavoriteStoreSuccess, getFavoriteStoreFail);
    };

    var getStoreNearbyFail = function (response) {
        $scope.model.searching = false;
        console.log('ERROR: ' + response);
    };

    var getFavoriteStoreSuccess = function (response) {
        if (!response.data || !response.data.store) {
            return;
        }
        var favoriteStore = response.data.store;
        var storesWithoutFavoriteStores = removeFavoriteStoreFromOtherStoreList(favoriteStore.id);

        var lat1 = StringUtilSrv.remplaceCommaByPoints(favoriteStore.latitude);
        var long1 = StringUtilSrv.remplaceCommaByPoints(favoriteStore.longitude);
        var lat2 = StoreLocatorSrv.getCurrentSearchLatitude();
        var long2 = StoreLocatorSrv.getCurrentSearchLongitude();

        var sortedStores = [];

        if(!(isNaN(lat2) && isNaN(long2))){

            var distance = Geocoder.calculateDistance(lat1, long1, lat2, long2);

            // Sync the same distance calculator algorithm
            favoriteStore.distance = distance;
            overrideDistance(lat2, long2);

            // Re-sorted store
            var sortedStores = sorterFromDistance(lat2, long2, storesWithoutFavoriteStores);

        }
        // Apply changes after digest process, to redraw the stores list.
        $timeout(function () {
            $scope.model.myStore = response.data.store;
            $scope.model.stores = sortedStores;
            $scope.$broadcast('RELOAD_STORE',$scope.model.myStore);
        });
    };

    /**
     * Override distance from backend to more precise algorithm in front end with {@link geolib}
     * @param currentSearchLat
     * @param currentSearchLon
     */
    var overrideDistance = function (currentSearchLat, currentSearchLon) {
        for (var i = 0; i < $scope.model.stores.length; i++) {
            var store = $scope.model.stores[i];
            store.distance = Geocoder.calculateDistance(currentSearchLat, currentSearchLon, store.latitude, store.longitude);
        }
    };

    /**
     * Sorter distance from backend to more precise algorithm in front end with {@link geolib}
     * @param currentSearchLat
     * @param currentSearchLon
     */
    var sorterFromDistance = function (currentSearchLat, currentSearchLon, stores) {
        var coordsSorted = geolib.orderByDistance({latitude: currentSearchLat, longitude: currentSearchLon}, stores);
        var storesSorters = [];
        for (var i = 0; i < coordsSorted.length; i++) {
            var coord = coordsSorted[i];
            storesSorters[i] = findStoreByCoords(coord.latitude, coord.longitude);
        }
        return storesSorters;
    };

    /**
     * Find {@link Store} by latitude & longitude.
     * @param lat
     * @param long
     * @returns {*}
     */
    var findStoreByCoords = function (lat, long) {
        if (!lat || !long) {
            return null;
        }
        for (var i = 0; i < $scope.model.stores.length; i++) {
            var store = $scope.model.stores[i];
            if (store.latitude === lat.toString() && store.longitude === long.toString()) {
                return store;
            }
        }
    };

    var getFavoriteStoreFail = function (response) {
        console.log('ERROR TO RETRIEVE STORE FAVORITE ' + response);
    };

    var fixLatitudeLongitudeStores = function (stores) {
        for (var i = 0; i < stores.length; i++) {
            var store = stores[i];
            store.latitude = StringUtilSrv.remplaceCommaByPoints(store.latitude);
            store.longitude = StringUtilSrv.remplaceCommaByPoints(store.longitude);
        }
    };

    var removeFavoriteStoreFromOtherStoreList = function (favoriteId) {
        var currentStores = $scope.model.stores;
        var newStoresList = [];
        for (var i = 0; i < currentStores.length; i++) {
            var store = currentStores[i];
            if (store.id !== favoriteId) {
                newStoresList.push(store);
            }
        }
        return newStoresList;
    };

    $scope.getStoreNearby = function () {
        if($scope.model.oldAddress !== $scope.model.address){
            $scope.model.oldAddress = $scope.model.address;

            $scope.model.searching = true;
            //TODO IMPROVE! ????
            $location.search('zipcode', ($scope.model.address));
        }
    };

    initialize();

});
