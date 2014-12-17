'use strict';


angular.module('PresentationFlow').directive('disableAnimation', function ($animate) {
    return {
        restrict: 'A',
        link: function ($scope, $element, $attrs) {
            $attrs.$observe('disableAnimation', function (value) {
                $animate.enabled(!value, $element);
            });
        }
    };
});

angular.module('PresentationFlow').controller('LandingCtrl', function ($scope,SessionDataSrv, RedirectSrv, $modal, LandingSrv, stBlurredDialog) {

    $scope.nextURL = '/landing'; //default

    $scope.slides = [
        {
            active: true,
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
        }

    ];

    var init = function (){
        var token = SessionDataSrv.getCurrentToken();
        if(token){
            $scope.needSYWLogin = false;
            LandingSrv.getHomeSessionInfo(token).then(successGetUserInfo, failGetUserInfo);
        }else {
            $scope.needSYWLogin = true;
        }
    };

    $scope.myInterval = 5000;
    $scope.needSYWLogin = true;
    $scope.typeModal = '';

    $scope.startNow = function () {
        if($scope.needSYWLogin){
            Platform.Authentication.openSignupDialog(null,null,'signIn');
            $scope.needSYWLogin = true;
        }else{
            RedirectSrv.redirectTo($scope.nextURL);
        }
    };


    var openFullScreenModal = function(template){
        stBlurredDialog.open(template);
    };

    var successGetUserInfo = function (response) {
        if(response){
            if(response.haveManualCars || response.haveLinkedCars ){
                RedirectSrv.redirectTo('/dashboard');
            } else if(response.haveNCDBCars && !response.haveManualCars && !response.haveLinkedCars) {
                openFullScreenModal('scripts/presentation-flow/views/linked-car.html');
            } else if(!response.haveNCDBCars && !response.haveManualCars && !response.haveLinkedCars) {
                openFullScreenModal('scripts/manage-car/add-car.html');
            }
        }
    };
    var failGetUserInfo = function (response) {
        console.log('ERROR: '+response);
    };

    /**
     * Calculate the when click next, the next URL path of the LandingPage.
     */
    init();

});
