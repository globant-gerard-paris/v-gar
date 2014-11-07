'use strict';

angular.module('PresentationFlow').controller('RecallsCtrl', function ($scope, RecallsSrv) {

    $scope.model = {
        recalls: []
    };

    var recallsResultSuccess = function (response) {
        $scope.model.recalls = response.data || [];
    };

    var recallsResultFaild = function (response) {
        console.log('ERROR: ' + response);
    };

    var modelYear = '2000',
            make = 'ITASCA',
            model = 'HORIZON';

    RecallsSrv.getRecalls(modelYear, make, model, recallsResultSuccess, recallsResultFaild);

});