'use strict';

angular.module('Services').service('SessionDataSrv', function () {

    /**
     * Return the Current userId session.
     * @returns {string}
     */
    var getCurrentUser = function () {
        return '1';//TODO: dummy user id until will be defined where we can take it.
    };

    return {
        getCurrentUser: getCurrentUser
    };

});