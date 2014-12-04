'use strict';


angular.module('PresentationFlow').controller('CarProfileHeaderCtrl', function ($scope,$http,$timeout) {

	var carsResultSuccess = function(response){
	    $scope.model.car = response.data.vehicles[0];
	};

    //MOCKK
    $timeout( function(){
        $http.get('resources/mocks/dashboard.json').then(carsResultSuccess);
    },0);
});
