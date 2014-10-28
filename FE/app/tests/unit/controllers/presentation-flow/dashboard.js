/* jshint -W117 */
'use strict';
describe('PresentationFlow Dashboard controller', function () {
    // save as T for easy calling
    var T = window.testHelpers,
            scope;

    T.bootstrapTest(
            ['PresentationFlow'], // modules
            ['RedirectSrv'], // services
            true  //expose service for direct access
            );

    beforeEach(function () {
        scope = T.createController('DashboardCtrl');
    });

    it('should be redirect to car profile page.', function () {
        scope.redirectToCarProfile();
        expect($location.path()).toEqual('/car-profile');
    });

    it('should be redirect to add car page.', function () {
        scope.redirectToAddCar();
        expect($location.path()).toEqual('/add-car');
    });

});