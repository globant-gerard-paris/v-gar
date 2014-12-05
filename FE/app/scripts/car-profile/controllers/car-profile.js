'use strict';


angular.module('CarProfile').controller('CarProfileCtrl2', function ($scope,$timeout,$http) {

    $scope.model = {};

	var carsResultSuccess = function(response){
	    $scope.model.data = response.data;
	    $scope.$emit('car-profile-data-ready');
	};
 
     //MOCK
    $timeout( function(){
        $http.get('resources/mocks/car-profile-data.json').then(carsResultSuccess);
    },500);

});
