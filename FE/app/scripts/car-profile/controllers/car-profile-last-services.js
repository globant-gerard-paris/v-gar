'use strict';


angular.module('PresentationFlow').controller('CarProfileLastServicesCtrl', function ($scope, $http) {

    var mock = false;

    var carsResultSuccess = function(response){
        $scope.model = {
            services : response.data.services,
            article: response.data.article
        };
    };

    if(mock){
        $http.get('resources/mocks/car-profile.json').then(carsResultSuccess);
    }
    else{
        //DashboardSrv.getCars(userId, carsResultSuccess, carsResultFailed);
    }
});
