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

angular.module('PresentationFlow').controller('DashboardCtrl', function ($scope, RedirectSrv, DashboardSrv, $http, SessionDataSrv) {

    var mock = false;

    var userId =  SessionDataSrv.getCurrentUser(); //'600';

    $scope.model = {
        cars: [],
        user: {
            userId: userId
        }
    };


    $scope.addCar = false;

    $scope.redirectToCarProfile = function () {
        RedirectSrv.redirectTo('/car-profile');
    };

    $scope.toggleAddCar = function () {
        $scope.addCar = !$scope.addCar;
    };



    $scope.getToCarProfile = function (option) {
        RedirectSrv.redirectTo('/car-profile?option=' + option);
    };

    var carsResultSuccess = function(response){
        $scope.model = {
            cars : response.data.vehicles
        };
    };

    var carsResultFailed = function (response) {
        console.log('ERROR: ' + response);
    };

    if(mock){
        $http.get('resources/mocks/dashboard.json').then(carsResultSuccess);
    }
    else{
        DashboardSrv.getCars(userId, carsResultSuccess, carsResultFailed);
    }
});