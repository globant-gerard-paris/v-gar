'use strict';

angular.module('Services').service('StringUtilSrv', function () {

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

    /**
     * Format the miles number to always show 1 decimal.
     * Ej: '123,0109238' ==> '123,0'
     * @param distance
     * @returns {*}
     */
    var formatDecimal = function (distance, length) {
        return parseFloat(Math.round(distance * 100) / 100).toFixed((length || 1));
    };

    /**
     * Format latitude and longitude.
     * Ej: '-80,14676545845658' ==> '-80.146765'
     * @param distance
     * @returns {*}
     */
    var formatCoordsNumber = function (coord) {
        var number = formatDecimal(String(coord), 6);
        return remplaceCommaByPoints(number);
    };

    return {
        remplaceCommaByPoints: remplaceCommaByPoints,
        formatDecimal: formatDecimal,
        formatCoordsNumber: formatCoordsNumber
    };
});