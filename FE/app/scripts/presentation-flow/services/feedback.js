'use strict';

/**
 *  @author Jero <jeronimo.carrizo@globant.com>
 */
angular.module('PresentationFlow').service('FeedbackSrv', function (ApiHttpSrv, SessionDataSrv, config) {

    var addFeedback = function (userId, feedback) {
        return ApiHttpSrv.createHttp('POST', config.api.hosts.BACKEND + '/feedback/user/' + userId, JSON.stringify(feedback));
    };

    return {
        addFeedback: addFeedback
    };
});