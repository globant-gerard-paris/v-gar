'use strict';

angular.module('PresentationFlow').controller('CarProfileCtrl', function ($scope, RedirectSrv) {

    $scope.searchStore = function(storeZipCode){
        RedirectSrv.redirectTo('/store-locator?zipcode='+(storeZipCode || ''));
    };

});