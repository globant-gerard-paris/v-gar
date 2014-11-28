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

angular.module('PresentationFlow').controller('LandingCtrl', function ($scope,SessionDataSrv, RedirectSrv, $modal, LandingSrv, $location) {

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
        fillTokenSession();

        var token = SessionDataSrv.getCurrentToken();
        console.log(">>>>>>>>>> INIT TOKEN: "+token);

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
        }
    };
    var failGetUserInfo = function (response) {
        console.log('ERROR: '+response);
    };

    var showObj = function(obj) {
        for (var p in obj) {
            console.log('>>> '+typeof(obj[p]), p);
        }
    };

    var fillTokenSession = function (){
        var currentToken = null;
        console.log('URRRRRRRLlllllllL URL JS 1: '+document.URL);
        console.log('URRRRRRRLlllllllL URL JS 2: '+window.location.href);
        console.log('URRRRRRRLlllllllL URL JS 3: '+window.location);
        console.log('URRRRRRRLlllllllL URL JS 4: '+window.location.origin);
//        console.log('URRRRRRRLlllllllL URL JS 5: '+JSON.stringify(window.location));
//        console.log('URRRRRRRLlllllllL URL JS 8 : '+JSON.stringify(parent.document.getElementsByTagName("IFRAME")));

        console.log('URRRRRRRLlllllllL URL: '+$location.url());
        console.log('URRRRRRRLlllllllL PATH 1: '+$location.path());
        console.log('URRRRRRRLlllllllL PATH 2: '+$location.absUrl());
        console.log('URRRRRRRLlllllllL PATH 3: '+$location.protocol());
        console.log('URRRRRRRLlllllllL PATH 4: '+$location.host());
        console.log('URRRRRRRLlllllllL PATH 5: '+$location.port());
        console.log('URRRRRRRLlllllllL PATH 6: '+JSON.stringify($location.search()));
        console.log('URRRRRRRLlllllllL PATH 7: '+JSON.stringify($location.hash()));
        console.log('URRRRRRRLlllllllL PATH 8: '+$location.replace());
        var indexOf = $location.toString().indexOf("token");
        if(indexOf !== -1){
            var indexStart = $location.toString().indexOf("token") + 6;
            var indexEnd = $location.toString().length;
            var token = $location.toString().substring(indexStart, indexEnd);
            //It is because SYW when you are not login put prefix token with 0_XXXXXX
            if(token){
                var userId = token.substring(0, token.indexOf("_"));
                if(userId && userId.length > 2){
                    currentToken = token;
                }
            }
        }

        console.log(">>>>>>>>>> DESDE EL LOCATION EL LOCATION: "+JSON.stringify($location.toString()));
        console.log(">>>>>>>>>> DESDE EL LOCATIO EL TOKEN ES: "+currentToken);
        window.vg = {
            config : {
                token : currentToken
            }
        };
    };



    /**
     * Calculate the when click next, the next URL path of the LandingPage.
     */
    init();

});