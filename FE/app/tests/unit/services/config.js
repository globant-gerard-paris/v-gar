/* jshint -W117 */
'use strict';
describe('Config services', function () {
    // save as T for easy calling
    var T = window.testHelpers;

    T.bootstrapTest(
        [], // modules
        ['ConfigSrv'],  // services
        true  //expose service for direct access
    );

    beforeEach(function () {
    });

    it('given a valid key to the getConfig method, should return the value of key defined.', function () {
        expect('http://127.0.0.1:9000').toEqual(ConfigSrv.getConfig('api.hosts.DEV'));
    });

    it('given a key undefined to the getConfig method, should return undefined.', function () {
        expect(undefined).toEqual(ConfigSrv.getConfig(undefined));
    });

    it('given a key without \'.\' to the getConfig method, should return the value of the key.', function () {
        expect(ConfigSrv.getConfig('api')).not.toBeNull();
    });

    it('given a valid key to the getApi URL method, should return the correct value.', function () {
        expect(ConfigSrv.getApiUrl('SYW_LOGIN')).toEqual('http://prod.sears/logint/prod');
    });

    it('given a invalid key to the getApi URL method, should return throw exception.', function () {
        try {
            expect(ConfigSrv.getApiUrl('1233333333333'));
        } catch (e) {
            expect(e).toMatch(/\w*ERROR\w*/);
        }
    });
});