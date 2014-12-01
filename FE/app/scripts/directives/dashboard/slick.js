'use strict';

angular.module('Directives').directive('slick', function () {
    return {
        restrict: 'A',
        scope: {
            data: '=slickData'
        },
        link: function(scope, el) {
            var unregister = scope.$watch('data', function(val){
                if(!angular.isArray(val) || val.length === 0){
                    return;
                }
                el.slick({
                    dots: false,
                    infinite: false,
                    autoplay: false,
                    slidesToShow: 3,
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
                                slidesToShow: 2
                            }
                        }
                    ]
                });
                unregister();
            });
        }
    };
});