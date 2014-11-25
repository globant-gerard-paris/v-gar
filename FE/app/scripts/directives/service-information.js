'use strict';

angular.module('Directives').directive('serviceInformation', function () {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/service-information.html',
        scope: {
            serviceDate: '=',
            serviceMileage: '=',
            serviceCenter: '=',
            serviceRecords: '=',
        },
        link: function (scope) {
            scope.serviceCenterStr = scope.serviceCenter.address +
                                    ' @ ' + scope.serviceCenter.city +
                                    ', '+ scope.serviceCenter.zipCode; 

            var serviceDescArr = [];


            _.each(scope.serviceRecords, function(record){
                serviceDescArr.push(record.description);
            })

            scope.serviceDesc = serviceDescArr.join(', ');
            
        }
    };
});