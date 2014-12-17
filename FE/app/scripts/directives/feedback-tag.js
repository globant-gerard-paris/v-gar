'use strict';

angular.module('Directives').directive('feedbackTag', function () {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/feedback-tag.html',
        scope: {
            verticalHeight: '='
        },
        link: function (scope) {
            scope.openFeedbackForm = function () {
                scope.$emit('OPEN_MODAL_FEEDBACK');
            };
        }
    };
});