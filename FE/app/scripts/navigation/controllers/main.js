'use strict';

angular.module('Navigation').controller('MainCtrl', function ($scope, $location, SessionDataSrv) {

    $scope.model = {
        userName : '',
        countLinkedCars : 0,
        sywNumber: 0,
        currentPath : $location.path(),
        currentCar : ''
    };

    $scope.$on('RELOAD_VEHICLES', function (/*event, dataResponse*/) {
        $scope.model.countLinkedCars =  SessionDataSrv.getCurrentFamilyVehicles().length;
        $scope.model.userName =  SessionDataSrv.getCurrentUserName();
        $scope.model.sywNumber =  SessionDataSrv.getSywMemberNumber();
    });

    $scope.$on('RELOAD_SELECTED_VEHICLE', function (/*event, dataResponse*/) {
        $scope.model.currentCar =  SessionDataSrv.getCurrentFamilyVehicle();
    });

    /**
     * Each time that reload the URL validate bradecrumb.
     */
    $scope.$on('$routeChangeSuccess', function () {
        console.log('CAMBIO EL PATHH '+$location.path());
        $scope.model.currentPath = $location.path();
    });

    $scope.isLandingPage = function(){
        return $location.path() === '/landing';
    };
    $scope.isDashboardPage = function(){
        return $location.path() === '/dashboard';
    };

    $scope.isCarProfilePage = function(){
        return $location.path() === '/car-profile';
    };

    $scope.isServicesOrRecallsPage = function(){
        return $location.path() === '/recalls' || $location.path() === '/services';
    };

    $scope.isFindServicePage = function(){
        return $location.path() === '/store-locator';
    };

    $scope.isMobile = function(){
        return SessionDataSrv.isMobileDevice();
    };

    $scope.getSywNumber = function(){
        if($scope.model.sywNumber){
            return $scope.model.sywNumber.substring(1, $scope.model.sywNumber.length - 1);
        }
        return '';
    };
});