'use strict';
module.exports = function(grunt) {
    grunt.config('ngAnnotate', {
        options: {
            singleQuotes: true
        },
        dist: {
            files: [
                {
                    expand: true,
                    cwd: '<%= grunt.config.dist %>/scripts/',
                    src: '*.js',
                    dest: '<%= grunt.config.dist %>/scripts/'
                }
            ]
        }
    });
};