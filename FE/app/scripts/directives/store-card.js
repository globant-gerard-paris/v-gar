/**
 *  The basic directive of {@link Store}.
 */
angular.module('Directives').directive('storeCard', function ($timeout, StoreLocatorSrv, StringUtilSrv) {
    'use strict';

    return {
        restrict: 'E',
        scope: {
            model: '='
        },

        templateUrl: 'scripts/directives/views/store-card.html',

        link: function (scope /*element, attributes*/) {
            //debugger
            if (scope.$parent.$last === true) {
                $timeout(function () {
                    //just for swiper
                    scope.$emit('NG_REPEAT_FINISHED');

                });
            }

            /**
             * Set the favorite store of the current user.
             */
            scope.setFavoriteStore = function () {
                StoreLocatorSrv.setFavoriteStore(scope.model.id, successSetFavoriteStore, faildSetFavoriteStore);
            };

            /**
             * Response success of the request set favorite store. Throw a event in order to reload the store-locator page
             * with the new favorite store.
             * @param response
             */
            var successSetFavoriteStore = function (response) {
                scope.$emit('SET_FAVORITE_STORE_SUCCESS', response);
            };

            /**
             * The response faild of the request set favorite store.
             * @param response
             */
            var faildSetFavoriteStore = function (response) {
                console.log('ERROR: ' + response);
            };

        }
    };
});
