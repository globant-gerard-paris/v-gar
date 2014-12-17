'use strict';


angular.module('PresentationFlow').controller('DashboardCtrl', function ($timeout, $scope, $modal, RedirectSrv, DashboardSrv, $http, SessionDataSrv, stBlurredDialog) {

    var mock = false,
        userId =  SessionDataSrv.getCurrentUser(),
        userName = SessionDataSrv.getCurrentUserName();
    $scope.model = {
        cars: [],
        user: {
            name: userName,
            userId: userId
        },
        linkApoinment : 'http://www.searsauto.com/stores/'+ SessionDataSrv.getCurrentFavoriteStore(),
        linkCoupon: 'http://www.searsauto.com/offers'
    };


    var openFullScreenModal = function(template){
        stBlurredDialog.open(template);
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
        if(response.data.store){
            SessionDataSrv.saveCurrentFavoriteStore(response.data.store.sacStore);
        }
        $scope.model.cars = response.data.vehicles;
        $scope.model.store = response.data.store;
        $scope.$broadcast('RELOAD_STORE',response.data.store);
        $scope.$emit('RELOAD_VEHICLES',response.data.vehicles);
    };

    var carsResultFailed = function (response) {
        console.log('ERROR: ' + response);
    };

    $scope.manageCars = function(){
        openFullScreenModal('scripts/presentation-flow/views/linked-car-modify.html');
    };

    $scope.$on('linked-cars-updated', function(){
        $scope.loadCars();
        stBlurredDialog.close();
    });

    $scope.openFeedbackForm = function(){
        $scope.$emit('OPEN_MODAL_FEEDBACK');
    };

    $scope.loadCars = function(){
        if(mock){
            $timeout( function(){
                $http.get('resources/mocks/dashboard.json').then(carsResultSuccess);
            },2000);

        }
        else{
            DashboardSrv.getCars(userId, carsResultSuccess, carsResultFailed);
        }
    };

    $scope.loadCars();



});
