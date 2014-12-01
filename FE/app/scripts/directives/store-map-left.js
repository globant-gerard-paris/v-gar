/**
 *  The basic directive of {@link Store}.
 */
angular.module('Directives').directive('storeMapLeft', function ($timeout, StoreLocatorSrv, StringUtilSrv) {
    'use strict';

    return {
        restrict: 'E',
        scope: {
            model: '='
        },

        templateUrl: 'scripts/directives/views/store-map-left.html',

        link: function (scope, element /*, attributes*/) {

            var SUNDAY_NUMBER = 0,
                map = element.find('.store-map')[0];

            if(!map){
                var m = 'Error, map canvas not found';
                alert(m);
                console.log(m);
            }

            scope.days = [];
            scope.hours = [];

            /**
             * Load days of the current week with the hours open and close of the store.
             */
            var load = function () {
                //TODO improve with promises
                var toDay = new Date();
                scope.days = toDay.getDaysOfTheCurrentWeek();
                if(!scope.model){return;}
                scope.hours[0] = scope.model.monday_open + 'am ' + scope.model.monday_close + 'pm';
                scope.hours[1] = scope.model.tuesday_open + 'am ' + scope.model.tuesday_close + 'pm';
                scope.hours[2] = scope.model.wednesday_open + 'am ' + scope.model.wednesday_close + 'pm';
                scope.hours[3] = scope.model.thursday_open + 'am ' + scope.model.thursday_close + 'pm';
                scope.hours[4] = scope.model.friday_open + 'am ' + scope.model.friday_close + 'pm';
                scope.hours[5] = scope.model.saturday_open + 'am ' + scope.model.saturday_close + 'pm';
                scope.hours[6] = scope.model.sunday_open + 'am ' + scope.model.sunday_close + 'pm';
                //TODO refactor:
                scope.currentDateIndex = (toDay.getDay() === SUNDAY_NUMBER) ? 6 : (toDay.getDay() - 1);
                scope.currentDate = scope.days[scope.currentDateIndex];

                var storePosition = new google.maps.LatLng(
                    StringUtilSrv.remplaceCommaByPoints(scope.model.latitude),
                    StringUtilSrv.remplaceCommaByPoints(scope.model.longitude)
                );

                var map_options = {
                    center: storePosition,
                    coords: storePosition,
                    zoom: 15,
                    streetViewControl: false,
                    scrollwheel: false,
                    mapTypeControl: false
                };

                var _map = new google.maps.Map(map, map_options);

                var marker = new google.maps.Marker({
                    position: storePosition,
                    map: _map,
                    title: scope.model.address
                });

                scope.map = _map;
                marker.setMap(_map);
            };

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
                scope.$parent.$close();
            };

            /**
             * The response faild of the request set favorite store.
             * @param response
             */
            var faildSetFavoriteStore = function (response) {
                console.log('ERROR: ' + response);
            };

            $timeout(function () {
                load();
            },1500);
        }
    };
});
