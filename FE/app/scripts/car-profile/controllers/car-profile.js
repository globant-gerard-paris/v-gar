'use strict';


angular.module('CarProfile').controller('CarProfileCtrl2', function ($scope, $timeout, $http, config, SessionDataSrv, ApiHttpSrv) {

    $scope.model = {};

    var mock = true;

	var carsResultSuccess = function(response){
	    $scope.model.data = response.data;
	    $scope.$emit('car-profile-data-ready');
	};


 
 	if(mock){
     //MOCK
	    $timeout( function(){
	        $http.get('resources/mocks/car-profile-data.json').then(carsResultSuccess);
	    },500);
	}else{
		var userId = SessionDataSrv.getCurrentUser();
		var familyVehicle = SessionDataSrv.getCurrentFamilyVehicle();
		var familyVehicleId = familyVehicle.id;
		ApiHttpSrv.createHttp('GET',config.api.hosts.BACKEND + '/car-profile/user/'+userId+'/familyvehicle/'+familyVehicleId ).then(carsResultSuccess);
	}

});
