/**
 *  The basic directive of {@link Store}.
 */
angular.module('Directives').directive('storeCard', function ($timeout, StoreLocatorSrv, $modal, SessionDataSrv) {
    'use strict';

    return {
        replace: true,
        restrict: 'EA',
        scope: {
            model: '='
        },

        templateUrl: 'scripts/directives/views/store/store-card.html',

        link: function (scope) {

            scope.seeMoreDetailPopup = function () {

                $modal.open({
                    //TODO replace for relative ?
                    template: '<store-map-left model="model"></store-map-left>',
                    scope: scope,
                    size: 'lg'
                    //controller: 'ModalInstanceCtrl',
                });

            };

            /**
             * Set the favorite store of the current user.
             */
            scope.setFavoriteStore = function () {
                SessionDataSrv.saveCurrentFavoriteStore(scope.model.sac_store);
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
