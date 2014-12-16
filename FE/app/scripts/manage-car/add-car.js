/**
 * Created by Crystian on 12/16/2014.
 */
'use strict';

angular.module('ManageCar',[]).controller('CarAddCtrl', function ($scope, RedirectSrv) {

    $scope.model.lala = 'se por model';

    $scope.addCarAction = function () {
        RedirectSrv.redirectTo('/dashboard');
    };

});
