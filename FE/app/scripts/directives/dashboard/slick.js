'use strict';

angular.module('Directives').directive('slick', function ($timeout) {
    return {
        restrict: 'A',
        scope: {
            data: '=slickData'
        },
        link: function(scope, el) {

            scope.$watch('data', function(val){
                if(!angular.isArray(val) || val.length === 0 || scope.hidden){
                    return;
                }

                scope.hidden = true;
                el.hide();
                el.unslick();
                var stickList = el.find('.slick-list');
                if(stickList){
                    stickList.remove();
                }
                

                $timeout( function(){
                    el.show();
                    scope.hidden = false;
                    // (re|)init
                    el.slick({
                        dots: false,
                        infinite: false,
                        autoplay: false,
                        slidesToShow: val.length < 3 ? val.length : 3,
                        slidesToScroll: 1,
                        respondTo: 'min',
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
