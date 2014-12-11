'use strict';

angular.module('Directives').directive('recallInformation', function (SessionDataSrv) {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/recall-information.html',
        scope: {
            recallDate: '=',
            modelYear: '=',
            make: '=',
            model: '=',
            component: '=',
            summary: '=',
            consequence: '=',
            remedy: '=',
            notes: '='
        },
        link: function (scope) {
            scope.isMobileDevice = function(){
                return SessionDataSrv.isMobileDevice();
            };
        }
    };
});