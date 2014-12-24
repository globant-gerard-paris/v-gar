'use strict';

angular.module('Directives').directive('recallsAndServices', function (RedirectSrv, $modal) {
    return {
        replace: true,
        restrict: 'E',
        templateUrl: 'scripts/directives/views/car-profile/recalls-and-services.html',
        scope: {
            model: '='
        },
        link: function (scope) {
            scope.redirectToRecalls = function () {
                RedirectSrv.redirectTo('/recalls');
            };

            scope.openRecommendedList = function () {
                $modal.open({
                    templateUrl: 'modalRecommendedList.html',
                    controller: 'ModalRecommendedCtrl',
                    windowClass: 'vg-record-modal',
                    backdropClass: 'vg-record-backdrop',
                    size: 'md',
                    resolve: {
                        context: function () {
                            return scope.model.data.recommendedService;
                        }
                    }
                });
            };
        }
    };
}).directive('recommendedServices', function () {
    return {
        replace: true,
        restrict: 'E',
        templateUrl: 'scripts/directives/views/car-profile/recommended-services.html',
        scope: {
            recommendedList: '='
        },
        link: function () {
        }
    };
}).controller('ModalRecommendedCtrl', function ($scope, $modalInstance, context) {

    $scope.recommendedService = context;

    $scope.close = function () {
        $modalInstance.dismiss('cancel');
    };

});