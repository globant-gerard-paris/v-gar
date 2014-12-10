/**
 *  The basic directive.
 */
angular.module('Directives').directive('carTellUsMore', function ($timeout) {
    'use strict';

    return {
        restrict: 'E',
        scope: {
            model: '=',
            type: '='
        },

        templateUrl: 'scripts/directives/views/car-profile/tell-us-more.html',

        link: function (scope /*, element , attributes*/) {
            scope.mobile = (scope.type === 'xs');// ? 'visible-xs': 'hidden-xs';

        }
    };
});
