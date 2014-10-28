/* jshint -W117 */
'use strict';
describe('Redirect services', function () {
    // save as T for easy calling
    var T = window.testHelpers;

    T.bootstrapTest(
        ['Services'], // modules
        ['RedirectSrv'],  // services
        true  //expose service for direct access
    );

    beforeEach(function () {
    });

    it('should save the history and redirecto to home.', function () {
        RedirectSrv.redirectTo('/home');
        var history = RedirectSrv.getHistory();
        expect(history[0]).toEqual('/home');
        expect($location.path()).toEqual('/home');
    });

    it('should redirect to previous the history.', function () {
        RedirectSrv.redirectTo('/home');
        RedirectSrv.redirectTo('/landing');
        RedirectSrv.redirectTo('/dashboard');

        RedirectSrv.redirectToPrev();

        var history = RedirectSrv.getHistory();
        expect($location.path()).toEqual('/landing');
        expect(history[0]).toEqual('/home');
        expect(history[1]).toEqual('/landing');
        expect(history[2]).toBe(undefined);
    });

    it('should redirect to first history token and not remove the first.', function () {
        RedirectSrv.redirectTo('/home');
        RedirectSrv.redirectTo('/landing');
        RedirectSrv.redirectTo('/dashboard');

        RedirectSrv.redirectToPrev();
        RedirectSrv.redirectToPrev();
        RedirectSrv.redirectToPrev();
        RedirectSrv.redirectToPrev();

        var history = RedirectSrv.getHistory();
        expect($location.path()).toEqual('/home');
        expect(history[0]).toEqual('/home');
    });

    it('should redirect to not found page.', function () {
        RedirectSrv.redirectTo404();
        expect($location.path()).toEqual('/404');
    });

    it('should redirect to error page.', function () {
        RedirectSrv.redirectToErrorPage();
        expect($location.path()).toEqual('/error');
    });
});