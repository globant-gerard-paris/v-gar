'use strict';
module.exports = function (grunt) {
    grunt.config('open', {
        server: {
            url: 'http://localhost:<%= connect.options.port %>'
//            url: 'http://sandbox.shopyourway.com/app/17510/l'
        },
        dist: {
            url: 'http://localhost:' + grunt.config.SERVER_DIST_PORT
        },
        e2e: {
            url: 'http://localhost:4444/wd/hub'
        },
        coverage: {
            path: function () {
                var reports = grunt.file.expand('coverage/PhantomJS*/index.html');
                return reports[reports.length - 1].toString();
            }
        }
    });
};