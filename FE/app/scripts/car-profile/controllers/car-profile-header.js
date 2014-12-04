'use strict';


angular.module('PresentationFlow').controller('CarProfileHeaderCtrl', function ($scope,$http,$timeout) {

	var carsResultSuccess = function(response){
	    $scope.model.car = response.data.vehicles[0];
	};

	$scope.model.alerts = {
		tireMessage : 'Maintance approaching due',
		oilMessage : 'OK',
		brakesMessage : 'Annual inspection approaching due'
	};

    //MOCKK
    $timeout( function(){
        $http.get('resources/mocks/dashboard.json').then(carsResultSuccess);
    },0);
});
