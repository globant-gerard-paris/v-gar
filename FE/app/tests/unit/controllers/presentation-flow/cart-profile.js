/* jshint -W117 */
'use strict';
describe('CarProfile controller', function () {
    // save as T for easy calling
    var T = window.testHelpers,
            scope;

    T.bootstrapTest(
            ['Services', 'PresentationFlow'], // modules
            ['PresentationFlowSrv', 'RedirectSrv'], // services
            true  //expose service for direct access
            );

    beforeEach(function () {
        scope = T.createController('CarProfileCtrl');
    });

    it('should be redirect to search store page.', function () {
        scope.searchStore();
        expect($location.path()).toEqual('/store-locator');
    });

    it('should be redirect to view recalls page.', function () {
        scope.viewRecalls();
        expect($location.path()).toEqual('/recalls');
    });

});