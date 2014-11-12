'use strict';

angular.module('PresentationFlow').service('LinkedCarSrv', function () {

    var getLinkedCars = function (successCallback) {
        successCallback([{
                year: 2008,
                make: 'Ford',
                model: 'Edge',
                mileage: '102,345',
                isConfirmed: true
            }, {
                year: 2010,
                make: 'Audi',
                model: 'A3',
                mileage: '10,685',
                isConfirmed: true
            }, {
                year: 2010,
                make: 'Audi',
                model: 'A3',
                mileage: '10,685',
                isConfirmed: true
            }, {
                year: 2010,
                make: 'Audi',
                model: 'A3',
                mileage: '10,685',
                isConfirmed: true
            }
        ]);
    };

    return {
        getLinkedCars: getLinkedCars
    };

});