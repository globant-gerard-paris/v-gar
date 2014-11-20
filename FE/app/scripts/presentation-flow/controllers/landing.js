'use strict';

angular.module('PresentationFlow').directive('disableAnimation', function($animate){
    return {
        restrict: 'A',
        link: function($scope, $element, $attrs){
            $attrs.$observe('disableAnimation', function(value){
                $animate.enabled(!value, $element);
            });
        }
    };
});

angular.module('PresentationFlow').controller('LandingCtrl', function ($scope, RedirectSrv,$modal, HomeServiceSrv) {


    $scope.slides = [
        {
            active:true,
            class: 'carousel_img_LP_1'
        },
        {
            class: 'carousel_img_LP_2'
        },
        {
            class: 'carousel_img_LP_3'
        },
        {
            class: 'carousel_img_LP_4'
        },
        {
            class: 'carousel_img_LP_5'
        }

    ];

    $scope.myInterval=5000;

    $scope.typeModal = '';
    $scope.startNow = function () {
        HomeServiceSrv.getUserSessionInfo().then(successGetUserInfo, failGetUserInfo);
    };

    $scope.open = function (destinationUrl) {

        var modalInstance = $modal.open({
            templateUrl: 'myModalContent.html',
            controller: 'ModalInstanceCtrl',
            size: 'md',
            resolve: {
                destinationPage : function () {
                    return destinationUrl;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            RedirectSrv.redirectTo(selectedItem);
        }, function () {
           // 'Modal dismissed at: ' + new Date()
        });
    };


    var successGetUserInfo = function(response){

        var action = 'algo!';

        switch (action) {
            case 'GUEST':
                $scope.typeModal = 'SIGN-UP';
                $scope.open('/add-car');
                break;
            case 'ONLY_SYW_USER':
                $scope.typeModal = 'SIGN-IN';
                $scope.open('/dashboard');
                break;
            case 'SIGNED_USER':
                RedirectSrv.redirectTo('/add-car');
                break;
            case 'FULL_USER':
                RedirectSrv.redirectTo('/dashboard');
                break;
            case 'NON_PARTICIPATE':
                RedirectSrv.redirectTo('/linked-car');
                break;
            default:
                RedirectSrv.redirectTo('/presentation-flow');
        }

    };
    var failGetUserInfo = function(response){

    };

}).controller('ModalInstanceCtrl', function ($scope, $modalInstance, destinationPage) {

    $scope.destinationPage = destinationPage;

    $scope.ok = function () {
        $modalInstance.close($scope.destinationPage);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});