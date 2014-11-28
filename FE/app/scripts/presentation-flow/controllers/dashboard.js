'use strict';


angular.module('PresentationFlow').filter('capitalize', function() {
    return function(input) {
        return (!!input) ? input.replace(/([^\W_]+[^\s-]*) */g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();}) : '';
    };
});

angular.module('PresentationFlow').directive('slick', function($timeout) {
    return function(scope, el) {
        $timeout(function() {
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
            });
        }, 500);
    };
});

angular.module('PresentationFlow').controller('DashboardCtrl', function ($scope, RedirectSrv, $http) {

    var mock = true;

    $scope.redirectToCarProfile = function () {
        RedirectSrv.redirectTo('/car-profile');
    };

    $scope.redirectToAddCar = function () {
        RedirectSrv.redirectTo('/add-car');
    };

    $scope.getToCarProfile = function (option) {
        RedirectSrv.redirectTo('/car-profile?option=' + option);
    };

    var servicesResultSuccess = function(response){
        $scope.model = {
            cars : response.data.vehicles
        };
    };

    if(mock){
        $http.get('resources/mocks/dashboard.json').then(servicesResultSuccess);
    }
    else{
        //ServicesSrv.getVehicleSomething
    }


});