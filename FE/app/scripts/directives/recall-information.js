'use strict';

angular.module('Directives').directive('recallInformation', function () {
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
            notes: '=',
            position: '='
        },
        link: function () {

        }
    };
});