'use strict';
angular.module('Directives').directive('storeRow', function ($timeout) {
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
            idStore: '='
        },
        templateUrl: 'scripts/directives/views/store-row.html',
        link: function (scope/*, element, attributes*/) {

            /**
             * Format the miles number to always show 1 decimal.
             *
             * @param distance
             * @returns {*}
             */
            scope.formatMilesAway = function (distance /*, element, attrs*/) {
                return parseFloat(Math.round(distance * 100) / 100).toFixed(1);
            };

            /**
             * Replace ',' character by '.' because 'google.maps.LatLng()' throw NaN exception with ',' character.
             *
             * @param text
             * @returns {*}
             */
            var remplaceCommaByPoints = function (text) {
                if (text) {
                    return text.replace(/,/g, '.');
                }
                return text;
            };


            var storePosition = new google.maps.LatLng(remplaceCommaByPoints(scope.latitude), remplaceCommaByPoints(scope.longitude));

            var map_options = {
                center: storePosition,
                coords: storePosition,
                zoom: 15
            };

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
            });
        }
    };
});