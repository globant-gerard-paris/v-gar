'use strict';


angular.module('PresentationFlow').controller('DashboardCtrl', function ($timeout, $scope, RedirectSrv, DashboardSrv, $http, SessionDataSrv) {

    var mock = false;

    var userId =  SessionDataSrv.getCurrentUser();

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

    $scope.redirectToFSC = function (search) {
        search = search ? '?zipcode='+search:'';
        RedirectSrv.redirectTo('/store-locator'+search);
    };

    $scope.toggleAddCar = function () {
        $scope.addCar = !$scope.addCar;
    };

    $scope.getToCarProfile = function (familyVehicle) {
        SessionDataSrv.saveCurrentFamilyVehicle(familyVehicle);
        $scope.$emit('RELOAD_SELECTED_VEHICLE',familyVehicle);
        RedirectSrv.redirectTo('/car-profile');
    };

    var carsResultSuccess = function(response){
        SessionDataSrv.saveCurrentFamilyVehicles(response.data.vehicles);
        $scope.model = {
            cars : response.data.vehicles,
            store: response.data.store
        };
        $scope.$broadcast('RELOAD_STORE',response.data.store);
        $scope.$emit('RELOAD_VEHICLES',response.data.vehicles);
    };

    var carsResultFailed = function (response) {
        console.log('ERROR: ' + response);
    };

    if(mock){
        $timeout( function(){
            $http.get('resources/mocks/dashboard.json').then(carsResultSuccess);
        },2000);

    }
    else{
        DashboardSrv.getCars(userId, carsResultSuccess, carsResultFailed);
    }
});
