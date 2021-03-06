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
    , 'ManageCar'
    , 'StoreLocator'
    , 'angulike'
    // yo:ngMainModules
];

angular.module('virtualGarage', _mainModules)

    .config(function ($routeProvider, $locationProvider, $httpProvider) {

        //for loader spinner
        $httpProvider.interceptors.push('httpInterceptor');

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
            name:'/',
            params: {
                templateUrl: 'scripts/presentation-flow/views/landing.html',
                controller: 'LandingCtrl'
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
            name: '/dashboard',
            params: {
                templateUrl: 'scripts/presentation-flow/views/dashboard.html',
                controller: 'DashboardCtrl'
            }
        });

        routes.push({
            name: '/car-profile',
            params: {
                templateUrl: 'scripts/car-profile/views/car-profile.html',
                controller: 'CarProfileCtrl'
            }
        });

        routes.push({
            name: '/store-locator',
            params: {
                templateUrl: 'scripts/store-locator/views/store-locator.html',
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

    }).run([
        '$rootScope', function ($rootScope) {
            //TODO In the future will need think in other better elegant way, because 'config.social.facebookAppID' is undefined.
            $rootScope.facebookAppId = '674196112699408';
        }
    ]);
