'use strict';

angular.module('PresentationFlow').controller('ServicesCtrl', function ($scope, RedirectSrv, ServicesSrv, $http, SessionDataSrv, $modal, RecordSrv) {

    $scope.model = {
        services: [],
        familyVehicle: SessionDataSrv.getCurrentFamilyVehicle()
    };

    $scope.$on('REMOVED_RECORD', function (/*event, dataResponse*/) {
        init();
    });

    var servicesResultSuccess = function (response) {

        $scope.model.services = response.data || [];

        $scope.model.services = _.sortBy($scope.model.services, function (srv) {
            return srv.date;
        }).reverse();

        $scope.model.years = [];

        $scope.model.groupedServices = _.groupBy($scope.model.services, function (srv) {
            var date = new Date(srv.date);
            var year = date.getFullYear();
            $scope.model.years.push(year);
            return year;
        });

        $scope.model.years = _.uniq($scope.model.years).sort().reverse();
    };

    var servicesResultFailed = function (response) {
        console.log('ERROR: ' + response);
    };

    var successGetServices = function (response) {
        $scope.model.servicesPopup = response.data;
    };

    var init = function () {
        var familyVehicle = SessionDataSrv.getCurrentFamilyVehicle();
        RecordSrv.getRecordService().then(successGetServices);
        ServicesSrv.getServices(familyVehicle.id, servicesResultSuccess, servicesResultFailed);
    };

    $scope.openNewRecordForm = function () {

        var modalNewRecord = $modal.open({
            templateUrl: 'modalNewRecord.html',
            controller: 'ModalServiceCtrl',
            windowClass: 'vg-record-modal',
            backdropClass: 'vg-record-backdrop',
            size: 'md',
            resolve: {
                context: function () {
                    return $scope.model;
                }
            }
        });

        modalNewRecord.result.then(function (model) {
            RecordSrv.addRecord(model.recordForm).then(successAddRecord, failAddRecord);
        }, function () {
            // 'Modal dismissed at: ' + new Date()
        });

        var successAddRecord = function (response) {
            init();
            $scope.$emit('NEWLY_ADDED_RECORD', response);
        };
        var failAddRecord = function (response) {
            alert('An error has occurred, please try again.');
            console.log('ERROR: ' + response);
        };
    };

    $scope.openInvoiceModal = function (service) {

        var modalNewRecord = $modal.open({
            templateUrl: 'modalInvoice.html',
            controller: 'ModalInvoiceCtrl',
            windowClass: 'vg-record-modal',
            backdropClass: 'vg-record-backdrop',
            size: 'md',
            resolve: {
                context: function () {
                    return service;
                }
            }
        });

        modalNewRecord.result.then(function (model) {
            console.log(model);
        }, function () {
            // 'Modal dismissed at: ' + new Date()
        });

    };

    $scope.$on('SHOW_INVOICE_MODAL', function (event, dataResponse) {
        $scope.openInvoiceModal(dataResponse);
    });


    /**
     * Initialize controller.
     */
    init();

}).controller('ModalServiceCtrl', function ($scope, $modalInstance, context) {

    $scope.model = context;
    $scope.model.recordForm = {
        mileage: null,
        date: null,
        source: null,
        comment: null,
        service: null
    };

    $scope.addRecord = function () {
        if ($scope.recordForm.$valid) {
            $modalInstance.close($scope.model);
        } else {
            $scope.recordForm.submitted = true;
        }
    };

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.opened = true;
    };

    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
}).controller('ModalInvoiceCtrl', function ($scope, $modalInstance, context) {

    $scope.model = {
        context: context
    };

    $scope.close = function () {
        $modalInstance.close($scope.model.context);
    };

});