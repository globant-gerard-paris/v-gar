'use strict';

angular.module('Directives').directive('serviceInformation', function () {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/service-information.html',
        scope: {
            serviceDate: '=',
            serviceMileage: '=',
            serviceCenter: '=',
            serviceDesc: '=',
        },
        link: function () {

        }
    };
});