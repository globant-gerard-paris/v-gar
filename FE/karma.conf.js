// Karma configuration
// Generated on Mon Oct 14 2013 14:16:36 GMT-0300 (ART)

module.exports = function(config) {
    config.set({

        // base path, that will be used to resolve files and exclude
        basePath: '',

        // frameworks to use
        frameworks: ['jasmine'],

        // list of files / patterns to load in the browser
        files: [
            // bower:js
            'app/bower_components/jquery/dist/jquery.js',
            'app/bower_components/es5-shim/es5-shim.js',
            'app/bower_components/angular/angular.js',
            'app/bower_components/json3/lib/json3.min.js',
            'app/bower_components/angular-route/angular-route.js',
            'app/bower_components/angular-resource/angular-resource.js',
            'app/bower_components/angular-sanitize/angular-sanitize.js',
            'app/bower_components/angular-cookies/angular-cookies.js',
            'app/bower_components/angular-animate/angular-animate.js',
            'app/bower_components/angular-touch/angular-touch.js',
            'app/bower_components/lodash/dist/lodash.compat.js',
            'app/bower_components/bootstrap/dist/js/bootstrap.js',
            'app/bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
            'app/bower_components/ngstorage/ngStorage.js',
            'app/bower_components/moment/moment.js',
            'app/bower_components/datejs/build/production/date.min.js',
            'app/bower_components/geolib/dist/geolib.js',
            'app/bower_components/swiper/dist/idangerous.swiper.js',
            // endbower files
            'app/bower_components/jasmine-expect/dist/jasmine-matchers.js',
            'app/bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js',
            'app/bower_components/angular-bootstrap/ui-bootstrap.js',
            'app/bower_components/angular-mocks/angular-mocks.js',
            'app/scripts/{,*/}/*.js',
            'app/scripts/{,*/}controllers/{,*/}*.js',
            'app/scripts/{,*/}services/{,*/}*.js',
            'app/scripts/{,*/}directives/{,*/}/*.js',
            'app/scripts/{,*/}filters/{,*/}*.js',
            'app/scripts/{,*/}util/{,*/}*.js',
            // test files
            'app/tests/helpers.js',
            'app/tests/unit/{,**/}*.js',
            'app/tests/unit/controllers/**/**.js',
            'app/tests/unit/services/**/**.js',
            'app/tests/unit/directives/**/**.js'
        ],

        // list of files to exclude
        exclude: [
        ],

        // test results reporter to use
        // possible values: 'dots', 'progress', 'junit', 'growl', 'coverage'
        reporters: ['coverage','progress'],

        preprocessors : {
            'app/scripts/{**/controllers/,**/services/,**/util/}*.js': ['coverage'],
            'app/scripts/directives/*.js': ['coverage'],
            'app/tests/unit/example.js': ['coverage'],
            '!app/scripts/*.js': ['coverage']
        },


        // configure the reporter
        coverageReporter: {
            type : 'html',
            dir : 'coverage/'
        },


        // web server port
        port: 9876,


        // enable / disable colors in the output (reporters and logs)
        colors: true,


        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,


        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: false,


        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: ['PhantomJS'],

        customLaunchers: {
            'PhantomJS_custom': {
                base: 'PhantomJS',
                options: {
                    windowName: 'PhantomJS TESTS',
                    settings: {
                        webSecurityEnabled: false,
                        loadImages: false

                    }
                },
                flags: [
                    '--ignore-ssl-errors=true',
                    '--load-images=false',
                    '--web-security=false'
                ]
            }
        },


        // If browser does not capture in given timeout [ms], kill it
        captureTimeout: 60000,


        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: true
    });
};
