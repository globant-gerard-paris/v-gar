'use strict';
module.exports = function(grunt) {
    var mockey_command = 'java -jar ./tools/mockey/Mockey.jar --location .';
    grunt.config('shell', {
        //run back-end server
        'runBKserver':{
            command: 'mvn spring-boot:run -Dhttp.proxyHost=166.76.3.199 -Dhttp.proxyPort=8080',
            options: {
                async: false,
                execOptions: {
                    cwd: '../BK'
                }
            }
        },
        'makeJARCompress':{
            command: 'build.bat local dist',
            options: {
                async: false,
                execOptions: {
                    cwd: '../'
                }
            }
        },
        'makeJARUncompress':{
            command: 'build.bat local app',
            options: {
                async: false,
                execOptions: {
                    cwd: '../'
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
