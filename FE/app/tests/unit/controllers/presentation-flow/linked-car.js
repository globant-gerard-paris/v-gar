/* jshint -W117 */
'use strict';
describe('LinkedCar controller', function () {
    // save as T for easy calling
    var T = window.testHelpers,
            scope;

    T.bootstrapTest(
            ['Services','PresentationFlow'], // modules
            ['PresentationFlowSrv', 'RedirectSrv'], // services
            true  //expose service for direct access
            );

    beforeEach(function () {
        scope = T.createController('LinkedCarCtrl');
    });
    
    it('should be redirect to add car page.', function () {
        scope.addCarAction();
        expect($location.path()).toEqual('/add-car');
    });

    it('should be redirect to dashboard page.', function () {
        scope.areMineAction();
        expect($location.path()).toEqual('/dashboard');
    });

});