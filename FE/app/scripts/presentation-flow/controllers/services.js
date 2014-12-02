'use strict';

angular.module('PresentationFlow').controller('ServicesCtrl', function ($scope, RedirectSrv, ServicesSrv, $http, SessionDataSrv) {

    var servicesResultSuccess = function (response) {

        $scope.model.services = response.data || [];

        $scope.model.services = _.sortBy($scope.model.services, function (srv) {
            return srv.date;
        }).reverse();

        $scope.model.years = [];

        $scope.model.groupedServices = _.groupBy($scope.model.services, function (srv) {
            var date = new Date(srv.date);
            var year = date.getFullYear();
            $scope.model.years.push(year);
            return year;
        });

        $scope.model.years = _.uniq($scope.model.years).sort().reverse();
    };

    var servicesResultFailed = function (response) {
        console.log('ERROR: ' + response);
    };

    var familyVehicle = SessionDataSrv.getCurrentFamilyVehicle();

    $scope.model = {
        services: [],
        familyVehicle: familyVehicle
    };

    ServicesSrv.getServices(familyVehicle.id, servicesResultSuccess, servicesResultFailed);

});