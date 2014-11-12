'use strict';

angular.module('Directives').directive('carConfirmation', function () {
    return {
        restrict: 'E',
        scope: {
            vehicule: '=',
            totalConfirmed: '='
        },
        templateUrl: 'scripts/directives/views/car-confirmation.html',
        link: function (scope) {
            scope.selectVehicule = function (vehicule) {
                vehicule.isConfirmed = !vehicule.isConfirmed;                       
            };
        },
        controller: function () {

        }
    };
});