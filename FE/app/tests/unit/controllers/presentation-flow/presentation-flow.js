/* jshint -W117 */
'use strict';
describe('PresentationFlow Menu Workflow controller', function () {
    // save as T for easy calling
    var T = window.testHelpers,
            scope;

    T.bootstrapTest(
            ['Services', 'PresentationFlow'], // modules
            ['PresentationFlowSrv', 'RedirectSrv'], // services
            true  //expose service for direct access
            );

    beforeEach(function () {
        scope = T.createController('PresentationFlowCtrl');
    });

    it('should be redirect to landing page.', function () {
        scope.userTypeAction('GUEST');
        expect($location.path()).toEqual('/presentation-flow-landing');
    });

    it('should be redirect to landing page.', function () {
        scope.userTypeAction('ONLY_SYW_USER');
        expect($location.path()).toEqual('/presentation-flow-landing');
    });

    it('should be redirect to landing page.', function () {
        scope.userTypeAction('SIGNED_USER');
        expect($location.path()).toEqual('/presentation-flow-landing');
    });

    it('should be redirect to dashboard page.', function () {
        scope.userTypeAction('FULL_USER');
        expect($location.path()).toEqual('/dashboard');
    });

    it('should be redirect to landing page.', function () {
        scope.userTypeAction('NON_PARTICIPATE');
        expect($location.path()).toEqual('/presentation-flow-landing');
    });

});