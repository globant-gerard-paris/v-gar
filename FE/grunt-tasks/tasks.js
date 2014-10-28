'use strict';

module.exports = function(grunt) {
	grunt.registerTask('serve', function (target) {
        if (target === 'dist') {
            return grunt.task.run(['build', 'open:dist', 'connect:dist:keepalive']);
        }
        grunt.task.run([
            'jshint',
            'htmllint',
            'bowerInstall',
            'compass:dev',
            'csslint',
            'bootlint',
            'start-mockey',
            'connect:livereload',
            'open:server',
            'ngconstant:development',
            'watch'
        ]);
    });

    //runs mockey from console and waits
    grunt.registerTask('run-mockey', ['shell:mockey']);

    //mockey async for internal use
    grunt.registerTask('start-mockey', ['shell:mockey-async']);
    grunt.registerTask('kill-mockey', ['shell:mockey-async:kill']);


    grunt.registerTask('run-e2e', [
        'connect:test',
        'selenium_start',
        'protractor:e2e',
        'selenium_stop'
    ]);

    grunt.registerTask('run-utest', [
        'karma:unit',
        'jshint',
        'bootlint',
        'htmllint',
        'open:coverage'
    ]);

    grunt.registerTask('test', [
        'run-utest',
        'run-e2e'
    ]);

    grunt.registerTask('build',function(target) {
        var tasks = [];

        tasks = tasks.concat([
            'jshint',
            'htmllint',
            'bootlint',
            'clean:dist',
            'bowerInstall',
            'compass:dist',
            'ngconstant:production',
            'useminPrepare',
            'concat',
            'concurrent:dist',
            'cssmin',
            'imagemin',
            'cdnify',
            'ngAnnotate',
            'uglify',
            'rev',
            'usemin',
            'copy:dist'
        ]);

        
        if (target === 'android') {
            tasks = tasks.concat([
                'clean:hybrid',
                'cordovacli:cordova_android',
                'clean:www',
                'copy:hybrid',
                'cordovacli:build_android',
                'cordovacli:emulate_android'
            ]);
        }

        if (target === 'ios') {
            tasks = tasks.concat([
                'clean:hybrid',
                'cordovacli:cordova_ios',
                'clean:www',
                'copy:hybrid',
                'cordovacli:build_ios',
                'cordovacli:emulate_ios'
            ]);
        }
        

        grunt.task.run(tasks);
    });

    grunt.registerTask('register-git-hooks', [
        'githooks'
    ]);

    grunt.registerTask('default', [
        'build'
    ]);

    grunt.registerTask('production', [
        'ngconstant:production'
    // Add further deploy related tasks here
    ]);

    grunt.registerTask('staging', [
        'ngconstant:staging'
    // Add further deploy related tasks here
    ]);

    // commit task for git
    grunt.registerTask('git-commit', [/*'htmllint','bootlint','jshint','test'*/]);
};
