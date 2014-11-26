'use strict';

angular.module('Directives').directive('carCard', function () {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/dashboard/car-card.html',
        scope: {
            car: '=',
        },
        link: function () {
        }
    };
});