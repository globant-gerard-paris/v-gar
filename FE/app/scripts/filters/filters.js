'use strict';

angular.module('Filters', [])
    .config(function () {})
    .filter( 'domain', function () {
		return function ( input ) {
			var output = '',
				matches,
				urls = /\w+:\/\/([\w|\.]+)/;
				
			matches = urls.exec( input );
        
			if ( matches !== null ) {
				output = matches[1];
			}
        
			return output;
		};
	});