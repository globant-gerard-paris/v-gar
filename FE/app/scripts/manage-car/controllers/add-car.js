/**
 * Created by Crystian on 12/16/2014.
 */
'use strict';

angular.module('ManageCar').controller('CarAddCtrl', function ($scope, RedirectSrv, stBlurredDialog, LinkedCarSrv, ManageCarSrv) {

    var states = {
        add:0,
        found:1,
        added:2
    };

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

        $scope.landing = (stBlurredDialog.getDialogData() && stBlurredDialog.getDialogData().from === 'landing');


        //TODO horrible hotfix, patch for X to close, on dashobard
        var el = document.getElementsByClassName('st-blurred-region-close')[0];
        el.classList.add('remove-position-of-x');
    }

    function loadingOn(){
        $scope.model.loading = true;
        $scope.model.available = false;
    }
    function loadingOff(){
        $scope.model.loading = false;
        $scope.model.available = true;
    }

    function errorInApi(response){
        console.log('ERROR: ' + response);
        alert('Error, please try again.');
    }

    $scope.close = function () {
        RedirectSrv.redirectTo('/dashboard');
        stBlurredDialog.close();
    };

    //FIRST STEP
    function refreshYears(){
        refreshMakes();

        ManageCarSrv.getYears(function (response) {
            $scope.model.years = response.data;
            loadingOff();
        }, errorInApi);
    }
    $scope.yearSelected = function() {
        loadingOn();
        refreshMakes();
    };

    function refreshMakes() {
        $scope.model.makeSelected = '';
        $scope.model.model = [];
        $scope.model.modelSelected = '';
        refreshModels();

        if($scope.model.yearSelected === ''){return;}

        ManageCarSrv.getMakes($scope.model.yearSelected, function (response) {
            $scope.model.makes = response.data;
            loadingOff();
        }, errorInApi);
    }
    $scope.makeSelected = function() {
        loadingOn();
        refreshModels();
    };

    function refreshModels() {
        $scope.model.models = [];
        $scope.model.modelSelected = '';
        $scope.model.mileageEnabled = false;

        if($scope.model.makeSelected === ''){return;}

        ManageCarSrv.getModels($scope.model.yearSelected, $scope.model.makeSelected, function (response) {
            $scope.model.models = response.data;
            loadingOff();
        }, errorInApi);
    }
    $scope.modelSelected = function() {
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


    //not used in fase 1, ready for adapt and modificate for fase 2
    $scope.addCar = function () {
        loadingOn();

        if (($scope.model.mileage === 0 ||$scope.model.mileage >= 0) &&
            $scope.model.modelSelected &&
            $scope.model.makeSelected &&
            $scope.model.yearSelected) {
            //the user fill al data, so this has priority over that license plate
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
        //setCar(loadingOff);
    };

    $scope.goBack = function () {
        switch ($scope.model.state){
        case states.add:
            if($scope.model.notFounded){
                $scope.model.notFounded = false;
                $scope.model.state = states.add;
            } else {
                if($scope.landing){
                    stBlurredDialog.open('scripts/presentation-flow/views/linked-car.html');
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

		var vehicle = {
            'familyVehicleId': null,
            'vehicleId': null,
            'make': $scope.model.makeSelected,
            'model': $scope.model.modelSelected,
            'year': $scope.model.yearSelected,
            'mileage': $scope.model.mileage,
            'name': null
        };

        ManageCarSrv.addCar(vehicle, function (/*response*/) {
            $scope.model.state = states.added;

            if (!$scope.landing) {
                $scope.$emit('linked-cars-updated');
            }

            cb();
        }, errorInApi);

    }


    init();
});
