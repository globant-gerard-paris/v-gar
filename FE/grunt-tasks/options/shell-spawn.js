'use strict';
module.exports = function(grunt) {
    var mockey_command = 'java -jar ./tools/mockey/Mockey.jar --location .';
    grunt.config('shell', {
        //run back-end server
        'runBKserver':{
            command: 'mvn spring-boot:run',
            options: {
                async: false,
                execOptions: {
                    cwd: '../BK'
                }
            }
        },

        //async
        'mockey-async':{
            command: mockey_command,
            options: {
                async: true,
                execOptions: {
                    cwd: './'
                }
            }
        },
        //sync
        'mockey':{
            command: mockey_command,
            options: {
                async: false,
                execOptions: {
                    cwd: './'
                }
            }
        },
        options: {
            stdout: true,
            stderr: true,
            failOnError: true
        }
    });
};
