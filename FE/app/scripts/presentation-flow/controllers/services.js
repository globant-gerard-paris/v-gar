'use strict';

angular.module('PresentationFlow').controller('ServicesCtrl', function ($scope) {

	$scope.model = {
		services : [
			{
				serviceDate: '22/07/2013',
	            serviceMileage: '1000',
	            serviceCenter: 'Test Center 1',
	            serviceDesc: 'Desc X1'
        	},
        	{
				serviceDate: '23/07/2013',
	            serviceMileage: '1001',
	            serviceCenter: 'Test Center 2',
	            serviceDesc: 'Desc X2'
        	},
        	{
				serviceDate: '24/07/2013',
	            serviceMileage: '1002',
	            serviceCenter: 'Test Center 3',
	            serviceDesc: 'Desc X3'
        	},
        	{
				serviceDate: '24/07/2013',
	            serviceMileage: '1003',
	            serviceCenter: 'Test Center 4',
	            serviceDesc: 'Desc X4'
        	}
		]
	};
});
