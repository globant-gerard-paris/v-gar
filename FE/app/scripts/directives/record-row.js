'use strict';

angular.module('Directives').directive('recordRow', function (RecordSrv) {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/record-row.html',
        scope: {
            record: '='
        },
        link: function (scope/*, element, attributes*/) {

            scope.removeRecord = function (recordId) {
                RecordSrv.deleteRecord(recordId).then(removeRecordSuccess, removeRecordFail);
            };

            var removeRecordSuccess = function (response) {
                scope.$emit('REMOVED_RECORD', response);
            };

            var removeRecordFail = function (response) {
                console.log('ERROR '+ response);
            };

        }
    };
});