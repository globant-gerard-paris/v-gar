/**
 * Created by Crystian on 12/16/2014.
 */
'use strict';

angular.module('ManageCar',[]).controller('CarAddCtrl', function ($scope, RedirectSrv, stBlurredDialog, LinkedCarSrv) {

    var states = {
        add:0,
        found:1,
        added:2
    };

    var mock = true;

    $scope.states = states;
    $scope.landing = false;

    $scope.model = {
        state: states.add,
        notFounded: false,
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

        btnAddCarSelectedDisabled: true
    };

    function init(){
        refreshYears();

        $scope.landing = (stBlurredDialog.getDialogData().from === 'landing');
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


    //SECOND STEP
    $scope.toggleCarSelect = function(car){
        if(!car){
            return;
        }
        var i = 0, l = $scope.model.vehicles.length,
            confirmTemp = car.isConfirmed;

        $scope.model.btnAddCarSelectedDisabled = true;

        for(;i<l;i++){
            $scope.model.vehicles[i].isConfirmed = false;
        }

        car.isConfirmed = !confirmTemp;

        if (car.isConfirmed) {
            $scope.model.btnAddCarSelectedDisabled = false;
        }
    };
    //END SECOND STEP


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
            var license = $scope.model.license = $scope.model.license.toUpperCase();

            if (license === 'ASD') {
                //TODO change it!
                LinkedCarSrv.getLinkedCars(function (response) {
                    $scope.model.state = states.found;
                    $scope.model.vehicles = response.data || [];
                    loadingOff();
                }, function (response) {
                    console.log('ERROR: ' + response);
                });

            } else {
                $scope.model.state = states.add;
                $scope.model.notFounded = true;

                loadingOff();
            }
        }
    };

    $scope.selectCar = function () {
        setCar(loadingOff);
    };

    $scope.goBack = function () {
        console.log('back');

        switch ($scope.model.state){
            case states.add:
                if($scope.model.notFounded){
                    $scope.model.notFounded = false;
                    $scope.model.state = states.add;
                } else {
                    if($scope.landing){
                        //TODO
                        console.log('TODO implement return to cars');

                    } else { //dashboard
                        stBlurredDialog.close();
                    }
                }
                break;
            case states.found:
                $scope.model.state = states.add;
                break;
        }

    };

    function setCar(cb){
        $scope.model.state = states.added;
        cb();
    }


    init();
});
