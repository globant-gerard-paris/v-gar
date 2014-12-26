'use strict';

angular.module('Directives').directive('recallsAndServices', function (RedirectSrv, $modal, CarProfileSrv, $rootScope) {
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

            var successBlock = function () {
                $rootScope.$broadcast('BLOCK_SUGGESTED_SERVICE');
            };

            scope.blockSuggested = function (suggested) {
                CarProfileSrv.blockSuggestedService(scope.model.data.recommendedService, suggested).then(successBlock);
            };
        }
    };
}).directive('recommendedServices', function (CarProfileSrv, $rootScope) {
    return {
        replace: true,
        restrict: 'E',
        templateUrl: 'scripts/directives/views/car-profile/recommended-services.html',
        scope: {
            recommended: '='
        },
        link: function (scope) {

            var successBlock = function () {
                $rootScope.$broadcast('BLOCK_SUGGESTED_SERVICE');
            };

            scope.blockSuggestedService = function (suggested) {
                CarProfileSrv.blockSuggestedService(scope.recommended, suggested).then(successBlock);
            };

        }
    };
}).controller('ModalRecommendedCtrl', function ($scope, $modalInstance, context) {

    $scope.recommendedService = context;

    $scope.close = function () {
        $modalInstance.dismiss('cancel');
    };

});