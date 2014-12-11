'use strict';

angular.module('Directives').directive('recallsAndServices', function (RedirectSrv) {
    return {
        replace: true,
        restrict: 'E',
        templateUrl: 'scripts/directives/views/car-profile/recalls-and-services.html',
        scope: {
            model: '='
        },
        link: function (scope) {
            scope.redirectToRecalls = function(){
                RedirectSrv.redirectTo('/recalls');
            };
        }
    };
});