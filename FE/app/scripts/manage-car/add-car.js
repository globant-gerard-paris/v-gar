/**
 * Created by Crystian on 12/16/2014.
 */
'use strict';

angular.module('ManageCar',[]).controller('CarAddCtrl', function ($scope, RedirectSrv) {


    var mock = true;

    $scope.model = {
        state: 0, //TODO constants: 0 = add car, 1 = license valid
        available: false,
        loading: true,

        //data
        years: [],
        yearSelected: '',
        makes: [],
        makeSelected: '',
        model: [],
        modelSelected: '',
        mileage: '',
        mileageEnabled: false,
        license: ''
    };

    function init(){
        refreshYears();
    }

    function loadingOn(){
        //console.log('loadingOn');
        $scope.model.loading = true;
        $scope.model.available = false;
    }
    function loadingOff(){
        //console.log('loadingOff');
        $scope.model.loading = false;
        $scope.model.available = true;
    }


    function refreshYears(){
        refreshMakes();
        if(mock){
            $scope.model.years = [2011,2012,2013,2014];
            loadingOff();
        } else {

        }
    }
    $scope.yearSelected = function() {
        //console.log('year selected!');
        loadingOn();
        refreshMakes();
    };

    function refreshMakes() {
        $scope.model.makeSelected = '';
        $scope.model.model = [];
        $scope.model.modelSelected = '';
        refreshModels();

        if($scope.model.yearSelected === ''){return;}

        if (mock) {
            $scope.model.makes = [{name:'name1',id:'1'},{name:'name2',id:'2'}];
            loadingOff();
        } else {

        }
    }
    $scope.makeSelected = function() {
        //console.log('make selected!');
        loadingOn();
        refreshModels();
    };

    function refreshModels() {
        $scope.model.models = [];
        $scope.model.modelSelected = '';
        $scope.model.mileageEnabled = false;

        if($scope.model.makeSelected === ''){return;}

        if (mock) {
            $scope.model.models = [{name:'model1',id:'1'},{name:'model2',id:'2'}];
            loadingOff();
        } else {

        }
    }
    $scope.modelSelected = function() {
        //console.log('model selected!');
        $scope.model.mileageEnabled = true;
    };



    init();

    $scope.addCar = function () {
        $scope.model.state = 1;
        //RedirectSrv.redirectTo('/dashboard');
    };

});
