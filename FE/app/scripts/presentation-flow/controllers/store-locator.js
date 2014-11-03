'use strict';

/**
 *  Jero
 */
angular.module('PresentationFlow').controller('StoreLocatorCtrl', function ($scope, StoreLocatorSrv, $location) {

    $scope.model = {
        stores: [],
        address : null
    };

    /**
     * This method initialize the controller.
     */
    var initialize = function(){
        var params = $location.search();
        if(params && params.zipcode){
            $scope.model.address = params.zipcode;
            StoreLocatorSrv.getStoreNearby($scope.model.address, storeResultSuccess, storeResultFaild);
        }
    };

    $scope.getStoreNearby = function(){
        StoreLocatorSrv.getStoreNearby($scope.model.address, storeResultSuccess, storeResultFaild);
    };

    var storeResultSuccess = function (response) {
        $scope.model.stores = response.data[0] || [];
    };
    var storeResultFaild = function (response) {
        console.log('ERROR: ' + response);
    };

    initialize();

});