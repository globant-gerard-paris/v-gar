'use strict';

angular.module('Directives').directive('serviceAlert', function () {
    return {
        replace: true,
        restrict: 'E',
        templateUrl: 'scripts/directives/views/car-profile/service-alert.html',
        scope: {
            model: '='
        },
        link: function () {
        }
    };
});