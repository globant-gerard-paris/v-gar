/**
 * Created by diego.caro on 25/11/2014.
 */
'use strict';

angular.module('Directives').directive('serviceInformation', function () {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/service-information.html',
        scope: {
            serviceDate: '=',
            mileage: '=',
            serviceCenter: '='
        },
        link: function () {

        }
    };
});
