'use strict';


angular.module('CarProfile').controller('CarProfileCtrl2', function ($scope) {

    $scope.model = {};

	var carsResultSuccess = function(response){
	    $scope.model.data = response.data;
	};
 
     //MOCKK
    $timeout( function(){
        $http.get('resources/mocks/carprofile.json').then(carsResultSuccess);
        $scope.$emit('car-profile-ready');
    },500);

});
