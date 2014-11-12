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
            // yo:ngMainModules
];


angular.module('virtualGarage', _mainModules)
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
                    templateUrl: 'scripts/presentation-flow/views/presentation-flow.html',
                    controller: 'PresentationFlowCtrl'
                }
            });

            routes.push({
                name: '/presentation-flow-landing',
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
                name: '/store-locator',
                params: {
                    templateUrl: 'scripts/presentation-flow/views/store-locator.html',
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

            // yo:ngRoutes

            routes.forEach(function (route) {
                $routeProvider.when(route.name, route.params);
            });

            $locationProvider.html5Mode(true);
        });