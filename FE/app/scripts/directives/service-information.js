'use strict';

angular.module('Directives').directive('serviceInformation', function () {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/service-information.html',
        scope: {
            service: '='
        },
        link: function (scope) {

            scope.serviceCenterStr = 'None';

            if (scope.service.serviceCenter) {
                scope.serviceCenterStr = scope.service.serviceCenter.address;
                scope.serviceCenterStr += ' ' + scope.service.serviceCenter.city +
                        ', ' + scope.service.serviceCenter.zipCode;
            }
            else {
                scope.serviceCenterStr = scope.service.source;
            }

            var serviceDescArr = [];

            _.each(scope.service.serviceRecordItems, function (record) {
                serviceDescArr.push(record.description);
            });

            scope.serviceDesc = serviceDescArr.join(', ');
            scope.serviceMileage = scope.service.mileage;

        }
    };
});