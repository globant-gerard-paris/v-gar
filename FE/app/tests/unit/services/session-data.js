/* jshint -W117 */
'use strict';
describe('SessionDataSrv', function () {
    // save as T for easy calling
    var T = window.testHelpers;

    T.bootstrapTest(
        ['Services'], // modules
        ['SessionDataSrv'],  // services
        true  //expose service for direct access
    );

    beforeEach(function () {
        spyOn(SessionDataSrv, 'getCurrentUser').and.callFake(function () {
            return '786';
        });
    });

    it('should be return the current userId.', function () {
        var userId = SessionDataSrv.getCurrentUser();
        expect(userId).toEqual('786');
    });

});