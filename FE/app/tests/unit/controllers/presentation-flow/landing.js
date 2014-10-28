/* jshint -W117 */
'use strict';
describe('PresentationFlow Landing controller', function () {
    // save as T for easy calling
    var T = window.testHelpers,
        scope;

    T.bootstrapTest(
        ['PresentationFlow'], // modules
        ['PresentationFlowSrv', 'RedirectSrv'],  // services
        true  //expose service for direct access
    );

    beforeEach(function () {
        scope = T.createController('LandingCtrl');
    });

//TODO: WILL FIXE IT
//    it('should be redirect to SIGN-UP in case that user login be GUEST.', function () {
//        PresentationFlowSrv.setUserTypeAction('GUEST');
//        scope.startNow();
//        expect($location.path()).toEqual('/sign-up');
//    });
//
//    it('should be redirect to SING-IN in case that user login be SYW.', function () {
//        PresentationFlowSrv.setUserTypeAction('ONLY_SYW_USER');
//        scope.startNow();
//        expect($location.path()).toEqual('/sign-in');
//    });

    it('should be redirect to add car in case that the user is signed.', function () {
        PresentationFlowSrv.setUserTypeAction('SIGNED_USER');
        scope.startNow();
        expect($location.path()).toEqual('/add-car');
    });

    it('should be redirect to dashboard in case that user login have all permission.', function () {
        PresentationFlowSrv.setUserTypeAction('FULL_USER');
        scope.startNow();
        expect($location.path()).toEqual('/dashboard');
    });

    it('should be redirect to linked car in case that user login not participate.', function () {
        PresentationFlowSrv.setUserTypeAction('NON_PARTICIPATE');
        scope.startNow();
        expect($location.path()).toEqual('/linked-car');
    });

    it('should be redirect to presentation flow again in case that user input wrong type action', function () {
        PresentationFlowSrv.setUserTypeAction('WRONNNN!!!!');
        scope.startNow();
        expect($location.path()).toEqual('/presentation-flow');
    });

});