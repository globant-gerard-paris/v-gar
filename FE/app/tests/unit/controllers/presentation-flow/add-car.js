/* jshint -W117 */
'use strict';
describe('AddCar controller', function () {
    // save as T for easy calling
    var T = window.testHelpers,
            scope;

    T.bootstrapTest(
            ['Services', 'PresentationFlow'], // modules
            ['PresentationFlowSrv', 'RedirectSrv'], // services
            true  //expose service for direct access
            );

    beforeEach(function () {
        scope = T.createController('AddCarCtrl');
    });

    it('should be redirect to dashboard page.', function () {
        scope.addCarAction();
        expect($location.path()).toEqual('/dashboard');
    });

});