'use strict';


angular.module('CarProfile').controller('CarProfileCtrl2', function ($scope, StoreLocatorSrv, $timeout) {

    $scope.model = {};

    var initialize = function () {
        console.log('init from car-profile');
        StoreLocatorSrv.getFavoriteStore(function (data) {
            $scope.model.myStore = data.data.store;
        }, function () {
            console.log('ERROR TO RETRIEVE STORE FAVORITE ' + response);
        });
    };



    initialize();

});
