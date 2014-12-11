'use strict';

angular.module('Directives').directive('carCard', function (SessionDataSrv, config, ApiHttpSrv) {
    return {
        replace: true,
        restrict: 'E',
        templateUrl: 'scripts/directives/views/dashboard/car-card.html',
        scope: {
            car: '=',
            changeMiles: '&'
        },
        link: function (scope) {
            var updateMileage = function(mileage){
                var familyVehicle = SessionDataSrv.getCurrentFamilyVehicle();
                var familyVehicleId = familyVehicle.id;
                 ApiHttpSrv.createHttp('POST', config.api.hosts.BACKEND + '/car-profile/familyvehicle/'+familyVehicleId+'/mileage', mileage);
            };

            scope.editMode = false;
            scope.toggleEditMiles = function(){
                if(!scope.car.newMileage){
                    scope.car.newMileage = scope.car.mileage;
                }
                if(scope.editMode){
                    if(scope.car.newMileage){
                        scope.car.mileage = scope.car.newMileage;
                        updateMileage(scope.car.mileage);
                    }
                }
                scope.editMode = !scope.editMode;
            };
        }
    };
});