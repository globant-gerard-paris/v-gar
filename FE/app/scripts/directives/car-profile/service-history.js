/**
 * Created by diego.caro on 04/12/2014.
 */
'use strict';

angular.module('Directives').directive('serviceHistory', function (RedirectSrv) {
    return {
        replace: true,
        restrict: 'E',
        templateUrl: 'scripts/directives/views/car-profile/service-history.html',
        scope: {
            services: '='
        },
        controller: function ($scope) {
            $scope.seeAllHistory = function () {
                RedirectSrv.redirectTo('/services');
            };
        },
        link: function (scope) {
            scope.$watch('services', function () {

                if (!scope.services) {
                    return;
                }

                var r = [];

                _.each(scope.services, function (service) {

                    var serviceCenter = service.serviceCenter,
                            serviceCenterStr = '',
                            serviceDescArr = [];

                    var serviceToReturn = {
                        serviceDate: service.date,
                        serviceMileage: service.mileage,
                        serviceType: service.type
                    };

                    _.each(service.serviceRecordItems, function (record) {
                        serviceDescArr.push(record.description);
                    });

                    if (serviceCenter) {
                        serviceCenterStr += serviceCenter.address + ' ' + serviceCenter.city +
                                ', ' + serviceCenter.zipCode;
                        serviceToReturn.serviceCenterStr = 'Sears Auto Center ' + serviceCenterStr;
                    } else {
                        serviceToReturn.serviceCenterStr = service.source;
                    }

                    serviceToReturn.serviceDesc = serviceDescArr.join(', ');
                    r.push(serviceToReturn);

                });

                scope.model = {
                    services: r
                };

            });


        }
    };
});
