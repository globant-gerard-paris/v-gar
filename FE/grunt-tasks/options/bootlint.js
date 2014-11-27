'use strict';
module.exports = function(grunt) {
	grunt.config('bootlint', {
        options: {
            stoponerror: true,
            relaxerror: [
                // Is relaxing the following error because is already defined in the 'index.html'

                'E001', //  Document is missing a DOCTYPE declaration
                'E003', //   Found one or more `.row`s that were not children of a grid column or descendants of a `.container` or `.container-fluid` (Is declared in Index.HTML in ng-view -->  <div class="container-fluid" ng-view></div>)
                'W001', // <head> is missing UTF-8 charset <meta> tag
                'W002', // <head> is missing X-UA-Compatible <meta> tag that disables old IE compatibility modes
                'W003', // <head> is missing viewport <meta> tag that enables responsiveness
                'W005', // Unable to locate jQuery, which is required for Bootstrap's JavaScript plugins to work
                'E013', // ??
				'E014', // ??
            ]
        },
        files: [
            '<%= grunt.config.app %>/scripts/**/views/*.html'
        ]
    });
};