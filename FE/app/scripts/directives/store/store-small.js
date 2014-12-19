/**
 *  The basic directive of {@link Store}.
 */
angular.module('Directives').directive('storeSmall', function (RedirectSrv) {
    'use strict';

    return {
        replace: true,
        restrict: 'E',
        scope: {
            model: '='
        },

        templateUrl: 'scripts/directives/views/store/store-small.html',

        link: function (scope /*, element , attributes*/) {

            scope.redirectToFindServiceCenter = function () {
                RedirectSrv.redirectTo('/store-locator');
            };

        }
    };
});
