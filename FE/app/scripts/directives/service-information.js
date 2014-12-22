'use strict';

angular.module('Directives').directive('serviceInformation', function ($modal, RecordSrv, RedirectSrv) {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/service-information.html',
        scope: {
            service: '='
        },
        link: function (scope) {

            scope.serviceCenterStr = 'None';

            if (scope.service.serviceCenter) {
                scope.serviceCenterStr = scope.service.serviceCenter.address;
                scope.serviceCenterStr += ' ' + scope.service.serviceCenter.city +
                        ', ' + scope.service.serviceCenter.zipCode;
            }
            else {
                scope.serviceCenterStr = scope.service.source;
            }

            var serviceDescArr = [];

            _.each(scope.service.serviceRecordItems, function (record) {
                serviceDescArr.push(record.description);
            });

            scope.serviceDesc = serviceDescArr.join(', ');
            scope.serviceMileage = scope.service.mileage;

            scope.removeRecord = function () {
                openConfirmationDialog();
            };

            scope.showInvoicePage = function (){
                scope.$emit('SHOW_INVOICE_MODAL', scope.service);
            };

            var openConfirmationDialog = function () {

                var modalNewRecord = $modal.open({
                    templateUrl: 'modalConfirmationDeleteRecord.html',
                    controller: 'ModalConfirmationDeleteServiceCtrl',
                    size: 'md',
                    resolve: {
                        context: function () {
                        }
                    }
                });

                modalNewRecord.result.then(function (model) {
                    console.log(model);
                    RecordSrv.deleteRecord(scope.service.id).then(removeRecordSuccess, removeRecordFail);
                }, function () {
                    //do nothing
                });

            };

            var removeRecordSuccess = function (response) {
                scope.$emit('REMOVED_RECORD', response);
            };

            var removeRecordFail = function (response) {
                console.log('ERROR ' + response);
            };

        }
    };
}).controller('ModalConfirmationDeleteServiceCtrl', function ($scope, $modalInstance) {

    $scope.close = function () {
        $modalInstance.close();
    };

});