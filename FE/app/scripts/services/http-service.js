'use strict';

/**
 * It is the base class to handle HTTP REST Request service in the application
 *
 * @author Jeronimo Carrizo <jeronimo.carrizo@globant.com>
 */
angular.module('Services').service('ApiHttpSrv', function ($http, ConfigSrv) {

    // creates an config object for $http, from params
    var createHttpConfig = function(type, url, data, extraHeaders, headersToDelete){
        //remove headers
        //set base headers
        var defaultHeaders = {
            'Content-Type': 'application/json',
            'Accept':'application/json'
        };
        //extend
        var headers = angular.extend(defaultHeaders, extraHeaders);
        if(headersToDelete){
            _.each(headersToDelete, function(deleteHeader){
                delete $http.defaults.headers.common[deleteHeader];
                delete headers[deleteHeader];
            });
        }

        return {method: type,
            url: url,
            data: data ? data : '',
            cache: false,
            headers: headers
        };
    };

    //creates an http object with required headers
    var createHttp = function(type, url, data, extraHeaders, headersToDelete){
        var config = createHttpConfig(type, url, data, extraHeaders, headersToDelete);
        // create http
        return $http(config);
    };

    //creates an http resource, from an api endpoint name
    var createApiHttp = function(type, name, data, params, headers, headersToDelete){
        // convert params object into a string of params, if not string
        if(params) {
            params = angular.isString(params) ? params : $.param(params);
        }
        // create url
        var url = ConfigSrv.getApiUrl(name) + (params ? '?'+params : '');
        // create http
        return createHttp(type, url, data, headers, headersToDelete);
    };

    //Response status form 200 to 299 are considered successful responses
    var isSuccessResponse = function(response) {
        return (response.status && response.status >= 200 && response.status <= 299);
    };

    return {
        createHttp: createHttp,
        createApiHttp: createApiHttp,
        isSuccessResponse: isSuccessResponse
    };
});