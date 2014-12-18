'use strict';

angular.module('Directives').directive('slick', function ($timeout) {
    return {
        restrict: 'A',
        scope: {
            data: '=slickData'
        },
        link: function(scope, el) {

            scope.$watch('data', function(val){
                if(!angular.isArray(val) || val.length === 0){
                    return;
                }

                // remove slick if data changed
                if(scope.initialized){
                    el.unslick();
                    scope.initialized = false;
                }

                scope.initialized = true;
                $timeout( function(){
                    // (re|)init
                    el.slick({
                        dots: false,
                        infinite: false,
                        autoplay: false,
                        slidesToShow: val.length < 3 ? val.length : 3,
                        slidesToScroll: 1,
                        responsive: [
                            {
                                breakpoint: 768,
                                settings: {
                                    slidesToShow: 1
                                }
                            },
                            {
                                breakpoint: 992,
                                settings: {
                                    slidesToShow: val.length < 2 ? val.length : 2
                                }
                            }
                        ]
                    });
                },500);

            });
        }
    };
});
