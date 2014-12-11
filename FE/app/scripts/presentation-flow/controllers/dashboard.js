'use strict';


angular.module('PresentationFlow').controller('DashboardCtrl', function ($timeout, $scope, $modal, RedirectSrv, DashboardSrv, FeedbackSrv, $http, SessionDataSrv) {

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
        RedirectSrv.redirectTo('/car-profile');
    };

    var carsResultSuccess = function(response){
        SessionDataSrv.saveCurrentFamilyVehicles(response.data.vehicles);
        $scope.model = {
            cars : response.data.vehicles,
            store: response.data.store
        };
        $scope.$broadcast('RELOAD_STORE',response.data.store);
    };

    var carsResultFailed = function (response) {
        console.log('ERROR: ' + response);
    };

    $scope.openFeedbackForm = function () {

        var modalNewRecord = $modal.open({
            templateUrl: 'modalFeedback.html',
            controller: 'ModalFeedbackCtrl',
            windowClass: 'vg-feedback-modal',
            backdropClass: 'vg-feedback-backdrop',
            size: 'md',
            resolve: {
                context: function () {
                    return $scope.model;
                }
            }
        });

        modalNewRecord.result.then(function (model) {
            console.log('Store feedback');
            FeedbackSrv.addFeedback(userId, model.recordForm).then(successAddFeedback, failAddFeedback);
        }, function () {
            // 'Modal dismissed at: ' + new Date()
        });

        var successAddFeedback = function (response) {
            $scope.$emit('NEWLY_ADDED_FEEDBACK', response);
        };
        var failAddFeedback = function (response) {
            alert('An error has occurred, please try again.');
            console.log('ERROR: ' + response);
        };
    };

    if(mock){
        $timeout( function(){
            $http.get('resources/mocks/dashboard.json').then(carsResultSuccess);
        },2000);

    }
    else{
        DashboardSrv.getCars(userId, carsResultSuccess, carsResultFailed);
    }

}).controller('ModalFeedbackCtrl', function ($scope, $modalInstance, context) {

    $scope.model = context;
    $scope.model.recordForm = {
        comment: null
    };

    $scope.addRecord = function () {
        if ($scope.recordForm.$valid) {
            $modalInstance.close($scope.model);
        }else{
            $scope.recordForm.submitted = true;
        }
    };

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});
