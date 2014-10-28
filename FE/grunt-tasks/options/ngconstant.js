'use strict';
module.exports = function(grunt) {

    var _ = require('lodash');
    var baseConfigName = 'base-config.json';

    var readConfigFile = function(filename) {
        var path = grunt.config.configFolder + filename;
        return grunt.file.readJSON(path);
    };

    var loadConfig = function(filename){
        if(grunt.config.configFolder) {
            // load base conf file
            var baseConfigJson = readConfigFile(baseConfigName);
            // loads a conf file
            var confJson = readConfigFile(filename);
            // extends the base with the new conf
            return _.merge({}, baseConfigJson, confJson);
        }else{
            return {};
        }
    };

    grunt.config('ngconstant', {
        options: {
            space: '    '
        },
        // Environment targets
        development: [{
            dest:  grunt.config.app+'/scripts/config.js',
            wrap: '/* jshint quotmark:true, indent:false, white: false */\n"use strict";\n\n <%= __ngModule %>',
            name: 'AppConfig',
            constants: loadConfig('dev-config.json')
        }],
        production: [{
            dest: grunt.config.app+'/scripts/config.js',
            wrap: '/* jshint quotmark:true, indent:false, white: false */\n"use strict";\n\n <%= __ngModule %>',
            name: 'AppConfig',
            constants: loadConfig('prod-config.json')
        }],
        staging: [{
            dest: grunt.config.app+'/scripts/config.js',
            wrap: '/* jshint quotmark:true, indent:false, white: false */\n"use strict";\n\n <%= __ngModule %>',
            name: 'AppConfig',
            constants:  loadConfig('stage-config.json')
        }]
    });
};