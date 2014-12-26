/**
 * Created by Crystian on 12/23/2014.
 */

'use strict';

angular.module('Directives').directive('loader', function () {

        return {
            restrict: 'EA',
            templateUrl: 'scripts/directives/views/loader.html',
            scope: {
                fullscreen: '='
            },
            link: function ($scope, element/*, attrs*/) {

                if ($scope.fullscreen) {
                    element.context.classList.add('fullscreen');
                } else {
                    element.context.classList.add('normal');
                    var el = element.context.getElementsByClassName('loader')[0];
                    el.innerHTML = '';
                }

                $scope.$on('loader_show', function () {
                    return element.show();
                });
                $scope.$on('loader_hide', function () {
                    return element.hide();
                });
            }
        };
    }
);
