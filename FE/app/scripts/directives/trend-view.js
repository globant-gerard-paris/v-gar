'use strict';

angular.module('Directives').directive('trendView', function () {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/trend-view.html',
        scope: {
            trend: '='
        },
        link: function () {

        }
    };
});