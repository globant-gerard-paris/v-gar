'use strict';
module.exports = function(grunt) {
    grunt.config('karma', {
	    unit: {
	        configFile: 'karma.conf.js'
	    },
        utest: {
            runnerPort: 9999,
            singleRun: true,
            browsers: ['PhantomJS_custom'],
            logLevel: 'ERROR',
            reporters: ['progress', 'coverage'],
            colors: true,
            autoWatch:false,
            reportSlowerThan:200
        },
        upush: {
            configFile: 'karma.conf.js',
            runnerPort: 9998,
            singleRun: true,
            browsers: ['PhantomJS_custom'],
            logLevel: 'ERROR',
            reporters: ['dots'],
            colors: false
        }
	});
};