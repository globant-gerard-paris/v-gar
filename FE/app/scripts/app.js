'use strict';

var _mainModules = [
    'Services'
            , 'Filters'
            , 'Directives'
            , 'ngRoute'
            , 'ngResource'
            , 'ngStorage'
            , 'ngSanitize'
            , 'ngCookies'
            , 'ngAnimate'
            , 'ngTouch'
            , 'ngLocale'
            , 'Error'
            , 'Navigation'
            , 'PresentationFlow'
            , 'CarProfile'
            // yo:ngMainModules
];

angular.module('virtualGarage', _mainModules)

//        .run(function(SessionDataSrv){
//            if(SessionDataSrv.isDataSave()){
//                SessionDataSrv.reload();
//            }
//        })

        .config(function ($routeProvider, $locationProvider) {
            //redirect any invalid hash to /home
            $routeProvider.otherwise({
                redirectTo: '/landing'
            });

            var routes = [];

            routes.push({
                name: '/404',
                params: {
                    templateUrl: 'scripts/error/views/404.html',
                    controller: 'ErrorCtrl'
                }
            });

            routes.push({
                name: '/error',
                params: {
                    templateUrl: 'views/unexpected-error.html',
                    controller: 'ErrorCtrl'
                }
            });

            routes.push({
                name: '/landing',
                params: {
                    templateUrl: 'scripts/presentation-flow/views/landing.html',
                    controller: 'LandingCtrl'
                }
            });

            routes.push({
                name: '/add-car',
                params: {
                    templateUrl: 'scripts/presentation-flow/views/add-car.html',
                    controller: 'AddCarCtrl'
                }
            });

            routes.push({
                name: '/dashboard',
                params: {
                    templateUrl: 'scripts/presentation-flow/views/dashboard.html',
                    controller: 'DashboardCtrl'
                }
            });

            routes.push({
                name: '/linked-car',
                params: {
                    templateUrl: 'scripts/presentation-flow/views/linked-car.html',
                    controller: 'LinkedCarCtrl'
                }
            });

            routes.push({
                name: '/car-profile',
                params: {
                    templateUrl: 'scripts/presentation-flow/views/car-profile.html',
                    controller: 'CarProfileCtrl'
                }
            });

            routes.push({
                name: '/car-profile2',
                params: {
                    templateUrl: 'scripts/car-profile/views/car-profile.html',
                    controller: 'CarProfileCtrl2'
                }
            });

            routes.push({
                name: '/store-locator',
                params: {
                    templateUrl: 'scripts/store-locator/store-locator.html',
                    controller: 'StoreLocatorCtrl'
                }
            });

            routes.push({
                name: '/recalls',
                params: {
                    templateUrl: 'scripts/presentation-flow/views/recalls.html',
                    controller: 'RecallsCtrl'
                }
            });

            routes.push({
                name: '/services',
                params: {
                    templateUrl: 'scripts/presentation-flow/views/services.html',
                    controller: 'ServicesCtrl'
                }
            });

            // yo:ngRoutes

            routes.forEach(function (route) {
                $routeProvider.when(route.name, route.params);
            });

            $locationProvider.html5Mode(true);
        });
