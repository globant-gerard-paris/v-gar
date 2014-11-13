'use strict';

angular.module('PresentationFlow').controller('LandingCtrl', function ($scope, RedirectSrv, PresentationFlowSrv,$modal) {


    $scope.slides = [
        {
            active:true,
            image: 'resources/images/garage.jpg'
        },
        {
            image: 'resources/images/car.jpg'
        },
        {
            image: 'resources/images/car2.jpg'
        }
    ];

    $scope.typeModal = '';
    $scope.startNow = function () {
        var action = PresentationFlowSrv.getUserTypeAction();
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

}).controller('ModalInstanceCtrl', function ($scope, $modalInstance, destinationPage) {

    $scope.destinationPage = destinationPage;

    $scope.ok = function () {
        $modalInstance.close($scope.destinationPage);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});