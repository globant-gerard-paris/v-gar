/**
 *  The basic directive.
 */
angular.module('Directives').directive('carTellUsMore', function () {
    'use strict';

    return {
        replace: true,
        restrict: 'E',
        scope: {
            model: '='
        },
        templateUrl: 'scripts/directives/views/car-profile/tell-us-more.html',
        link: function () {

        }
    };
});
