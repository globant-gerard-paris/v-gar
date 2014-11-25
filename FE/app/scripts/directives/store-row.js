'use strict';

/**
 *  The basic directive of {@link Store Row}.
 *  @author Jero <jeronimo.carrizo@globant.com>
 */
angular.module('Directives').directive('storeRow', function ($timeout, StoreLocatorSrv, StringUtilSrv) {
    return {
        restrict: 'E',
        scope: {
            address: '=',
            districtName: '=',
            districtNumber: '=',
            city: '=',
            distance: '=',
            zipCode: '=',
            latitude: '=',
            longitude: '=',
            idStore: '=',
            mondayOpen: '=',
            mondayClose: '=',
            tuesdayOpen: '=',
            tuesdayClose: '=',
            wednesdayOpen: '=',
            wednesdayClose: '=',
            thursdayOpen: '=',
            thursdayClose: '=',
            fridayOpen: '=',
            fridayClose: '=',
            saturdayOpen: '=',
            saturdayClose: '=',
            sundayOpen: '=',
            sundayClose: '=',
            isFavoriteStore: '=',
            phone : '='
        },

        templateUrl: 'scripts/directives/views/store-row.html',

        link: function (scope/*, element, attributes*/) {

            scope.days = [];
            var SUNDAY_NUMBER = 0;

            /**
             * Load days of the current week with the hours open and close of the store.
             */
            var initDays = function () {
                var toDay = new Date();
                scope.days = toDay.getDaysOfTheCurrentWeek();
                scope.days[0] += ' ' + scope.mondayOpen + 'am ' + scope.mondayClose + 'pm';
                scope.days[1] += ' ' + scope.tuesdayOpen + 'am ' + scope.tuesdayClose + 'pm';
                scope.days[2] += ' ' + scope.wednesdayOpen + 'am ' + scope.wednesdayClose + 'pm';
                scope.days[3] += ' ' + scope.thursdayOpen + 'am ' + scope.thursdayClose + 'pm';
                scope.days[4] += ' ' + scope.fridayOpen + 'am ' + scope.fridayClose + 'pm';
                scope.days[5] += ' ' + scope.saturdayOpen + 'am ' + scope.saturdayClose + 'pm';
                scope.days[6] += ' ' + scope.sundayOpen + 'am ' + scope.sundayClose + 'pm';
                scope.currentDate = (toDay.getDay() === SUNDAY_NUMBER) ? scope.days[6] : scope.days[(toDay.getDay() - 1)];
            };

            /**
             * The current store position
             * @type {google.maps.LatLng}
             */
            var storePosition = new google.maps.LatLng(StringUtilSrv.remplaceCommaByPoints(scope.latitude), StringUtilSrv.remplaceCommaByPoints(scope.longitude));

            /**
             *  The options of the map.
             * @type {{center: google.maps.LatLng, coords: google.maps.LatLng, zoom: number}}
             */
            var map_options = {
                center: storePosition,
                coords: storePosition,
                zoom: 15
            };

            /**
             * Is function is executed after digest process in order to draw the maps & marker in the page.
             */
            $timeout(function () {
                var storeElementId = document.getElementById('map_canvas_' + scope.idStore);
                var map = new google.maps.Map(storeElementId, map_options);

                var marker = new google.maps.Marker({
                    position: storePosition,
                    map: map,
                    title: scope.address
                });

                scope.map = map;
                marker.setMap(map);
            }, 500);

            /**
             * Format miles number with 1 decimal.
             * @param miles
             * @returns {*}
             */
            scope.formatMilesAway = function (miles) {
                return  StringUtilSrv.formatDecimal(miles);
            };

            /**
             * Set the favorite store of the current user.
             */
            scope.setFavoriteStore = function () {
                StoreLocatorSrv.setFavoriteStore(scope.idStore, successSetFavoriteStore, faildSetFavoriteStore);
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


            /**
             * Redirect to Store Page.
             */
            scope.redirectToStorePage = function(){
              //TODO: already don't exist the Store page.
            };

            /**
             * Display Days with the hours that are Open and Close this {@link Store).
             */
            initDays();
        }
    };
});
