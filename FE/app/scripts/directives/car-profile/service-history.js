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
            services: '='
        },

        link: function (scope) {
            scope.$watch( 'services', function(){

                if(!scope.services){return;}

                var r = [];

                _.each(scope.services, function(service){

                    var serviceCenter = service.serviceCenter,
                        serviceCenterStr = '',
                        serviceDescArr = [];

                    var serviceToReturn = {
                        serviceDate: service.date,
                        serviceMileage: service.mileage,
                        serviceType: service.type
                    };

                    _.each(service.serviceRecordItems, function(record){
                        serviceDescArr.push(record.description);
                    });

                    if (serviceCenter) {
                        serviceCenterStr += serviceCenter.address;
                        serviceCenterStr += serviceCenter.city ? ' @ ' + serviceCenter.city +
                                            ', ' + serviceCenter.zipCode : '';
                        serviceToReturn.serviceCenterStr = serviceCenterStr;
                        serviceToReturn.serviceDesc = serviceDescArr.join(', ');
                        r.push(serviceToReturn);
                    } else {
                        serviceCenterStr = service.source;
                    }

                });

                scope.model = {
                    services : r
                };


                //scope.serviceCenterStr = 'None';
                //
                //if (scope.serviceCenter !== null) {
                //    scope.serviceCenterStr = scope.serviceCenter.address;
                //
                //    if(scope.serviceCenter.city){
                //        scope.serviceCenterStr += ' @ ' + scope.serviceCenter.city +
                //            ', ' + scope.serviceCenter.zipCode;
                //    }
                //}
                //
                //var serviceDescArr = [];
                //
                //_.each(scope.serviceRecords, function(record){
                //    serviceDescArr.push(record.description);
                //});
                //
                //scope.serviceDesc = serviceDescArr.join(', ');


            });


        }
    };
});
