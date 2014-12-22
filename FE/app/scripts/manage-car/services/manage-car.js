/**
 * Created by Crystian on 12/18/2014.
 */

'use strict';

angular.module('PresentationFlow').service('ManageCarSrv', function ($timeout, $http, ApiHttpSrv, config, SessionDataSrv) {

    var mock = false;

    var getYears = function (successCallback, faildCallback) {
        if(!mock){
            ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/vehicle/manualvehicle/year/years')
                .then(successCallback, faildCallback);
        }else{
            $timeout( function(){
                successCallback({data:[2011,2012,2013,2014,2015]});
            },800);
        }
    };

    var getMakes = function (year, successCallback, faildCallback) {
        if(!mock){
            ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/vehicle/manualvehicle/year/'+ year +'/make/makes')
                .then(successCallback, faildCallback);
        }else{
            $timeout( function(){
                successCallback({data:['from mock name1','name2']});
            },800);
        }
    };

    var getModels = function (year, make, successCallback, faildCallback) {
        if(!mock){
            ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/vehicle/manualvehicle/year/'+year+'/make/'+make+'/model/models')
                .then(successCallback, faildCallback);
        }else{
            $timeout( function(){
                successCallback({data:['from mock model1','model2']});
            },800);
        }
    };

    var addCar = function (vehicle, successCallback, faildCallback) {
        if(!mock){
            ApiHttpSrv.createHttp('POST', config.api.hosts.BACKEND + '/vehicle/manualvehicle/user/'+SessionDataSrv.getCurrentUser(), vehicle)
                .then(successCallback, faildCallback);
        }else{
            $timeout(successCallback ,800);
        }
    };

    return {
        getMakes:getMakes,
        getYears: getYears,
        getModels: getModels,
        addCar: addCar
    };

});
