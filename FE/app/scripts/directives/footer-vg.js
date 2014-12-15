'use strict';

angular.module('Directives').directive('footerVg', function () {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/footer-vg.html',
        link: function(scope){
            scope.openFeedbackForm = function(){
                scope.$emit('OPEN_MODAL_FEEDBACK');
            };
        }
    };
});