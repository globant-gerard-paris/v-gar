'use strict';

angular.module('PresentationFlow').controller('DashboardCtrl', function ($scope, RedirectSrv) {

    $scope.redirectToCarProfile = function () {
        RedirectSrv.redirectTo('/car-profile');
    };

    $scope.redirectToAddCar = function () {
        RedirectSrv.redirectTo('/add-car');
    };

    $scope.getToCarProfile = function (option) {
        RedirectSrv.redirectTo('/car-profile?option=' + option);
    };

    $scope.model = {
    	cars : [
    		{
	            year: '2014',
	            brand: 'Chevrolet',
	            model: 'Colorado',
	            mileage: '44000',
	            nameTag: 'My Little Luxury'
    		}
    	]
	};

});