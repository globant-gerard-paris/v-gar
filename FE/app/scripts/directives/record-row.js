'use strict';

angular.module('Directives').directive('recordRow', function (RecordSrv, $q, $modal) {
    return {
        restrict: 'E',
        scope: {
            record: '='
        },
        templateUrl: 'scripts/directives/views/record-row.html',
        link: function (scope/*, element, attributes*/) {

            scope.removeRecord = function () {
                openConfirmationDialog();
            };

            var openConfirmationDialog = function () {

                var modalNewRecord = $modal.open({
                    templateUrl: 'modalConfirmationDeleteRecord.html',
                    controller: 'ModalConfirmationDeleteCtrl',
                    size: 'md',
                    resolve: {
                        context: function () {
                        }
                    }
                });

                modalNewRecord.result.then(function (model) {
                    console.log(model);
                    RecordSrv.deleteRecord(scope.record.id).then(removeRecordSuccess, removeRecordFail);
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
}).controller('ModalConfirmationDeleteCtrl', function ($scope, $modalInstance) {

    $scope.ok = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss();
    };
});