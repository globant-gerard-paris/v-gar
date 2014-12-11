/**
 * Created by diego.caro on 04/12/2014.
 */
'use strict';

angular.module('Directives').directive('carArticle', function () {
    return {
        replace: true,
        restrict: 'E',
        templateUrl: 'scripts/directives/views/car-profile/car-article.html',
        scope: {
            model: '='
        },
        link: function () {
        }
    };
});