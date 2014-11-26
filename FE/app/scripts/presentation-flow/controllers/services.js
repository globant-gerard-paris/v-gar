'use strict';

angular.module('PresentationFlow').controller('ServicesCtrl', function ($scope, RedirectSrv, ServicesSrv, $http) {

    $scope.model = {
        services: []
    };

    var mock = true;


    /*
        var groupByDate = function(arr){
        var res = {};
        _.each(arr, function(val){
            var date = new Date(val.date);
            var year = date.getFullYear();
            if(!angular.isArray(res[year])){
                res[year] = []
            }
            res[year].push(val);
        });
        return res;
    };
    */

    var servicesResultSuccess = function (response) {
        $scope.model.services = response.data || [];

        //$scope.model.services = _.orderBy($scope.model.services, );

        $scope.model.services = _.sortBy($scope.model.services, function(srv){
            return srv.date;
        }).reverse();

        $scope.model.years = [];

        $scope.model.groupedServices = _.groupBy($scope.model.services, function(srv){
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

    var familyId = '8105575',
        tangibleId = '75224002';

    $scope.model = {
        services: [],
        vehicle: {
            familyId: familyId,
            tangibleId: tangibleId
        }
    };



    if(mock){
        $http.get('resources/mocks/services.json').then(servicesResultSuccess, servicesResultFailed);
    }
    else{
        ServicesSrv.getServices(familyId, tangibleId, servicesResultSuccess, servicesResultFailed);
    }

});