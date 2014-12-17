/**
 * Created by Crystian on 12/16/2014.
 */
'use strict';

angular.module('ManageCar',[]).controller('CarAddCtrl', function ($scope, RedirectSrv, stBlurredDialog) {

    var states = {
        add:0,
        found:1,
        notFound:2,
        added:3
    };

    var mock = true;

    $scope.states = states;
    $scope.model = {
        state: states.add, //TODO constants: 0 = add car, 1 = license valid
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
        license: '',

        cars: [],
        carSelect: {}
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

    $scope.close = function () {
        RedirectSrv.redirectTo('/dashboard');
        stBlurredDialog.close();
    };

    //FIRST STEP
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
    //END FIRST STEP



    $scope.addCar = function () {
        loadingOn();

        if (($scope.model.mileage === 0 ||$scope.model.mileage >= 0) &&
            $scope.model.modelSelected &&
            $scope.model.makeSelected &&
            $scope.model.yearSelected) {
            //the user fill al data, so this has priority over that license plate
            console.log('user fill all data');
            setCar(loadingOff);
        } else {
            console.log('looking for license plate');

            if ($scope.model.license === 'ASD') {
                $scope.model.state = states.found;
                loadingOff();
            } else {
                $scope.model.state = states.notFound;
                loadingOff();
            }
        }
    };

    $scope.selectCar = function () {
        setCar(loadingOff);
    };

    function setCar(cb){
        $scope.model.state = states.added;
        cb();
    }


    init();
});
