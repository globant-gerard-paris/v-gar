'use strict';

/**
 *  @author Jero <jeronimo.carrizo@globant.com>
 */
angular.module('PresentationFlow').controller('RecordCtrl', function ($scope, $modal, RecordSrv) {

    $scope.model = {
        dateRecordFormat: 'MM/dd/yyyy',
        services: RecordSrv.getRecordService()
    };

    $scope.openNewRecordForm = function () {

        var modalNewRecord = $modal.open({
            templateUrl: 'modalNewRecord.html',
            controller: 'ModalNewRecordCtrl',
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
            $scope.$emit('NEWLY_ADDED_RECORD', response);
        };
        var failAddRecord = function (response) {
            alert('An error has occurred, please try again.');
            console.log('ERROR: ' + response);
        };
    };
}).controller('ModalNewRecordCtrl', function ($scope, $modalInstance, context) {

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
        }else{
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
});