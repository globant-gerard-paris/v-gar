/**
 *  The basic directive of {@link Store}.
 */
angular.module('Directives').directive('store', function ($timeout, StoreLocatorSrv, StringUtilSrv) {
    'use strict';

    return {
        restrict: 'E',
        scope: {
            model: '='
        },

        templateUrl: 'scripts/directives/views/store.html',

        link: function (scope, element/*, attributes*/) {


            var SUNDAY_NUMBER = 0,
                map = element.find('.store-map')[0];

            scope.days = [];
            scope.hours = [];

            if(!map){
                var m = 'Error, map canvas not found';
                alert(m);
                console.log(m);
            }

            //TODO yes, it needs to improve, you can do it!
            scope.$on('RELOAD_STORE', function (event, dataResponse) {
                scope.model = dataResponse;
                load();
            });

            /**
             * Load days of the current week with the hours open and close of the store.
             */
            var load = function () {
                //TODO improve with promises
                var toDay = new Date();
                scope.days = toDay.getDaysOfTheCurrentWeek();
                if(!scope.model){return;}
                scope.hours[0] = scope.model.mondayOpen + 'am ' + scope.model.mondayClose + 'pm';
                scope.hours[1] = scope.model.tuesdayOpen + 'am ' + scope.model.tuesdayClose + 'pm';
                scope.hours[2] = scope.model.wednesdayOpen + 'am ' + scope.model.wednesdayClose + 'pm';
                scope.hours[3] = scope.model.thursdayOpen + 'am ' + scope.model.thursdayClose + 'pm';
                scope.hours[4] = scope.model.fridayOpen + 'am ' + scope.model.fridayClose + 'pm';
                scope.hours[5] = scope.model.saturdayOpen + 'am ' + scope.model.saturdayClose + 'pm';
                scope.hours[6] = scope.model.sundayOpen + 'am ' + scope.model.sundayClose + 'pm';
                //TODO refactor:
                scope.currentDateIndex = (toDay.getDay() === SUNDAY_NUMBER) ? 6 : (toDay.getDay() - 1);
                scope.currentDate = scope.days[scope.currentDateIndex];


                /**
                 * The current store position
                 * @type {google.maps.LatLng}
                 */
                var storePosition = new google.maps.LatLng(
                    StringUtilSrv.remplaceCommaByPoints(scope.model.latitude),
                    StringUtilSrv.remplaceCommaByPoints(scope.model.longitude)
                );


                /**
                 * Is function is executed after digest process in order to draw the maps & marker in the page.
                 */
                //$timeout(function () {
                    /**
                     *  The options of the map.
                     * @type {{center: google.maps.LatLng, coords: google.maps.LatLng, zoom: number}}
                     */
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
                    title: scope.model.address,
                    icon: '../resources/images/store-locator/searsauto.jpg'
                });

                scope.map = _map;
                marker.setMap(_map);

                //}, 1000);
            };

            $timeout(function () {
                load();
            },200);
        }
    };
});
