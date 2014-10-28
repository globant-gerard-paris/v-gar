'use strict';

angular.module('Services', ['AppConfig']).service('ConfigSrv', function (config) {

    //assert helper for config service
    var _assert = function(expression, msg){
        if(!expression) {
            throw 'ConfigSrv ERROR: ' + msg;
        }
    };

    var keypath = function(content, key){
        var originalKeyString, keys, partialContent;

        if( !_.isString(key) || _.isEmpty(content)){
            return;
        }

        if( key.indexOf('.') === -1 ){
            return content[key] || undefined;
        }else{
            partialContent = content;
            originalKeyString = key;
            keys = key.split('.');

            keys.forEach(function(key){
                if( !partialContent ){
                    return;
                }

                partialContent = partialContent[key] || undefined;
            });

            return partialContent;
        }
    };

    //get config by json path, for example getConfig('x.y.z')
    var getConfig = function(path){
        return keypath(config,path);
    };

    // get api url by method name
    var getApiUrl = function(method_name) {
        //get Method Config
        var methodConf = getConfig('api.methods.'+method_name);
        _assert(methodConf, 'api method '+method_name+' is not defined in api.methods');
        //get current host
        var hostName = methodConf.use_host;
        _assert(hostName, 'api method '+method_name+' use_host not defined');
        //get host address
        var hostAddr = '';
        if(hostName !== 'LOCAL') {
            hostAddr = getConfig('api.hosts.' + hostName);
            _assert(hostAddr === '' || hostAddr, 'api method ' + method_name + ' host name ' + hostName + ' is not defined in api.hosts');
        }
        // get method path for the host
        var methodPath = methodConf.host_paths ? methodConf.host_paths[hostName] : false;
        _assert(methodPath, 'api method '+method_name+' path is not defined for host '+hostName);
        // return all the url
        return hostAddr + methodPath;
    };

    //return service
    return {
        getConfig: getConfig,
        getApiUrl: getApiUrl
    };
});