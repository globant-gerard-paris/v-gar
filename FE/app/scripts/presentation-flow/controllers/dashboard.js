'use strict';


angular.module('PresentationFlow').directive('slick', function($timeout) {
    return function(scope, el, attrs) {
        $timeout((function() {
            el.slick({
                dots: false,
                infinite: false,
                autoplay: false,
                slidesToShow: 3,
                slidesToScroll: 1,
                responsive: [
                    {
                      breakpoint: 768,
                      settings: {
                        slidesToShow: 1
                      }
                    },
                    {
                      breakpoint: 992,
                      settings: {
                        slidesToShow: 2
                      }
                    }
                ]
            })
        }), 500)
    }
})

angular.module('PresentationFlow').controller('DashboardCtrl', function ($scope, RedirectSrv, $timeout) {

    $scope.redirectToCarProfile = function () {
        RedirectSrv.redirectTo('/car-profile');
    };

    $scope.redirectToAddCar = function () {
        RedirectSrv.redirectTo('/add-car');
    };

    $scope.getToCarProfile = function (option) {
        RedirectSrv.redirectTo('/car-profile?option=' + option);
    };

    $timeout(function(){
    	$scope.model.clock = {x:1};
    },1000);

    $scope.model = {
    	clock: {},
    	cars : [
    		{
	            year: '2014',
	            brand: 'Chevrolet',
	            model: 'Colorado',
	            mileage: '44000',
	            nameTag: 'My Little Luxury'
    		},
    		{
	            year: '2001',
	            brand: 'Kia',
	            model: 'Sportage',
	            mileage: '134000',
	            nameTag: 'The Monster'
    		},
  		{
	            year: '2012',
	            brand: 'Ford',
	            model: 'Fiesta',
	            mileage: '12000',
	            nameTag: 'My Mom\'s Car'
    		},
    		{
	            year: '2001',
	            brand: 'KiaA',
	            model: 'Sportage',
	            mileage: '134000',
	            nameTag: 'The Monster'
    		},
  		{
	            year: '2014',
	            brand: 'ChevroletB',
	            model: 'Colorado',
	            mileage: '44000',
	            nameTag: 'My Little Luxury'
    		},
    		{
	            year: '2001',
	            brand: 'KiaB',
	            model: 'Sportage',
	            mileage: '134000',
	            nameTag: 'The Monster'
    		}
    	]
	};

});