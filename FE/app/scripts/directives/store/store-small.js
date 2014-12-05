/**
 *  The basic directive of {@link Store}.
 */
angular.module('Directives').directive('storeSmall', function () {
    'use strict';

    return {
        restrict: 'E',
        scope: {
            model: '='
        },

        templateUrl: 'scripts/directives/views/store/store-small.html',

        link: function (/*scope, element , attributes*/) {


        }
    };
});
