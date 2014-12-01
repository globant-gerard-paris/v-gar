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

angular.module('PresentationFlow').controller('LandingCtrl', function ($scope,SessionDataSrv, RedirectSrv, $modal, LandingSrv) {

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
        },
        {
            class: 'carousel_img_LP_5'
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

    var successGetUserInfo = function (response) {
        if(response){
            if(response.haveManualCars || response.haveLinkedCars ){
                $scope.nextURL = '/dashboard';
            } else if(response.haveNCDBCars && !response.haveManualCars && !response.haveLinkedCars) {
                $scope.nextURL = '/linked-car';
            } else if(!response.haveNCDBCars && !response.haveManualCars && !response.haveLinkedCars) {
                $scope.nextURL = '/add-car';
            }
            RedirectSrv.redirectTo($scope.nextURL);
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