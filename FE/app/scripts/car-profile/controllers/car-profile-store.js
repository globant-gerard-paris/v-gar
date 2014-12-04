
'use strict';


angular.module('CarProfile').controller('CarProfileStoreCtrl', function ($scope, StoreLocatorSrv) {

    var initialize = function () {
        console.log('init from car-profile-store');
        StoreLocatorSrv.getFavoriteStore(function (response) {
            $scope.model.myStore = response.data.store;
        }, function (error) {
            console.log('ERROR TO RETRIEVE STORE FAVORITE ' + error);
        });
    };



    initialize();

});
