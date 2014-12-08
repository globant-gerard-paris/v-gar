/**
 * Created by diego.caro on 04/12/2014.
 */
'use strict';

angular.module('Directives').directive('serviceHistory', function () {
    return {
        replace: true,
        restrict: 'E',
        templateUrl: 'scripts/directives/views/car-profile/service-history.html',
        scope: {
            serviceDate: '=',
            serviceMileage: '=',
            serviceCenter: '=',
            serviceRecords: '=',
            iconPrivate: '='
        },
        link: function (scope) {

            scope.serviceCenterStr = 'None';

            if (scope.serviceCenter !== null) {
                scope.serviceCenterStr = scope.serviceCenter.address;
                if(scope.serviceCenter.city){
                    scope.serviceCenterStr += ' @ ' + scope.serviceCenter.city +
                        ', ' + scope.serviceCenter.zipCode;
                }
            }

            var serviceDescArr = [];

            _.each(scope.serviceRecords, function(record){
                serviceDescArr.push(record.description);
            });

            scope.serviceDesc = serviceDescArr.join(', ');

        }
    };
});
