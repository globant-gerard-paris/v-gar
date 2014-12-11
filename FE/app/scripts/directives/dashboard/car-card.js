'use strict';

angular.module('Directives').directive('carCard', function () {
    return {
        replace: true,
        restrict: 'E',
        templateUrl: 'scripts/directives/views/dashboard/car-card.html',
        scope: {
            car: '=',
            changeMiles: '&'
        },
        link: function (scope) {
            scope.editMode = false;
            scope.toggleEditMiles = function(){
                if(!scope.car.newMileage){
                    scope.car.newMileage = scope.car.mileage;
                }
                if(scope.editMode){
                    if(scope.car.newMileage){
                        scope.car.mileage = scope.car.newMileage;
                        //call service here
                    }
                }
                scope.editMode = !scope.editMode;
            };
        }
    };
});