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

            //var SUNDAY_NUMBER = 0,
            //    map = element.find('.store-map')[0];
			//
            //scope.days = [];
            //scope.hours = [];
			//
            //if(!map){
            //    var m = 'Error, map canvas not found';
            //    alert(m);
            //    console.log(m);
            //}
			//
            ///**
            // * Load days of the current week with the hours open and close of the store.
            // */
            //var init = function () {
            //    //TODO improve with promises
            //    var toDay = new Date();
            //    scope.days = toDay.getDaysOfTheCurrentWeek();
            //    scope.hours[0] = scope.model.mondayOpen + 'am ' + scope.model.mondayClose + 'pm';
            //    scope.hours[1] = scope.model.tuesdayOpen + 'am ' + scope.model.tuesdayClose + 'pm';
            //    scope.hours[2] = scope.model.wednesdayOpen + 'am ' + scope.model.wednesdayClose + 'pm';
            //    scope.hours[3] = scope.model.thursdayOpen + 'am ' + scope.model.thursdayClose + 'pm';
            //    scope.hours[4] = scope.model.fridayOpen + 'am ' + scope.model.fridayClose + 'pm';
            //    scope.hours[5] = scope.model.saturdayOpen + 'am ' + scope.model.saturdayClose + 'pm';
            //    scope.hours[6] = scope.model.sundayOpen + 'am ' + scope.model.sundayClose + 'pm';
            //    //TODO refactor:
            //    scope.currentDateIndex = (toDay.getDay() === SUNDAY_NUMBER) ? 6 : (toDay.getDay() - 1);
            //    scope.currentDate = scope.days[scope.currentDateIndex];
			//
			//
            //    /**
            //     * The current store position
            //     * @type {google.maps.LatLng}
            //     */
            //    var storePosition = new google.maps.LatLng(
            //        StringUtilSrv.remplaceCommaByPoints(scope.model.latitude),
            //        StringUtilSrv.remplaceCommaByPoints(scope.model.longitude)
            //    );
			//
			//
            //    /**
            //     * Is function is executed after digest process in order to draw the maps & marker in the page.
            //     */
            //    //$timeout(function () {
            //    /**
            //     *  The options of the map.
            //     * @type {{center: google.maps.LatLng, coords: google.maps.LatLng, zoom: number}}
            //     */
            //    var map_options = {
            //        center: storePosition,
            //        coords: storePosition,
            //        zoom: 15,
            //        streetViewControl: false,
            //        scrollwheel: false,
            //        mapTypeControl: false
            //    };
			//
            //    var _map = new google.maps.Map(map, map_options);
			//
            //    var marker = new google.maps.Marker({
            //        position: storePosition,
            //        map: _map,
            //        title: scope.model.address
            //    });
			//
            //    scope.map = _map;
            //    marker.setMap(_map);
			//
            //    //}, 1000);
            //};
			//
            //$timeout(function () {
            //    init();
            //},500);
        }
    };
});
