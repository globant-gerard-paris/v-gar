'use strict';

angular.module('Navigation').controller('MainCtrl', function ($scope, $location, SessionDataSrv, config, $modal, FeedbackSrv) {
    $scope.model = {
        userName: '',
        countLinkedCars: 0,
        sywNumber: 0,
        currentPath: $location.path(),
        currentCar: '',
        backButtonMobileLink: '/dashboard',
        appoinmentUrl: config.extUrl.appoinment + SessionDataSrv.getCurrentFavoriteStore()
    };
    var updateAppoinment = function () {
        $scope.model.appoinmentUrl = config.extUrl.appoinment + SessionDataSrv.getCurrentFavoriteStore();
    };
    var reloadVehicleInformation = function () {
        $scope.model.currentCar = SessionDataSrv.getCurrentFamilyVehicle();
        if($scope.model.countLinkedCars = SessionDataSrv.getCurrentFamilyVehicles()){
            $scope.model.countLinkedCars = SessionDataSrv.getCurrentFamilyVehicles().length;
        }
    };
    var reloadUserInformation = function () {
        $scope.model.userName = SessionDataSrv.getCurrentUserName();
        $scope.model.sywNumber = SessionDataSrv.getSywMemberNumber();
    };
    $scope.$on('RELOAD_VEHICLES', function (/*event, dataResponse*/) {
        reloadVehicleInformation();
        reloadUserInformation();
        updateAppoinment();
    });
    $scope.$on('RELOAD_SELECTED_VEHICLE', function (/*event, dataResponse*/) {
        reloadVehicleInformation();
    });
    $scope.$on('SET_FAVORITE_STORE_SUCCESS', function (/*event, dataResponse*/) {
        updateAppoinment();
    });
    $scope.isMobile = function () {
        return SessionDataSrv.isMobileDevice();
    };
    $scope.isLandingPage = function () {
        return $location.path() === '/landing';
    };
    $scope.isDashboardPage = function () {
        return $location.path() === '/dashboard';
    };
    $scope.isCarProfilePage = function () {
        return $location.path() === 'C';
    };
    $scope.isServicesOrRecallsPage = function () {
        return $location.path() === '/recalls' || $location.path() === '/services';
    };
    $scope.isFindServicePage = function () {
        return $location.path() === '/store-locator';
    };
    $scope.isMobile = function () {
        return SessionDataSrv.isMobileDevice();
    };
    $scope.getSywNumber = function () {
        if ($scope.model.sywNumber) {
            return $scope.model.sywNumber.substring(1, $scope.model.sywNumber.length - 1);
        }
        return '';
    };
    /**
     * Each time that reload the URL validate bradecrumb.
     */
    $scope.$on('$routeChangeSuccess', function () {
        $scope.model.currentPath = $location.path();
        if ($scope.isServicesOrRecallsPage()) {
            $scope.model.backButtonMobileLink = '/car-profile';
            $scope.model.backButtonMobileText = $scope.model.currentCar.vehicle.make;
        } else {
            $scope.model.backButtonMobileLink = '/dashboard';
            $scope.model.backButtonMobileText = 'Home';
        }
    });
    $scope.$on('OPEN_MODAL_FEEDBACK', function () {
        $scope.openFeedbackForm();
    });

    var init = function () {
        reloadVehicleInformation();
        reloadUserInformation();
        updateAppoinment();
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
            FeedbackSrv.addFeedback(SessionDataSrv.getCurrentUser(), model.recordForm).then(successAddFeedback, failAddFeedback);
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

    init();

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