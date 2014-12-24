'use strict';

angular.module('Directives').directive('footerVg', function (config) {
    return {
        restrict: 'E',
        templateUrl: 'scripts/directives/views/footer-vg.html',
        link: function (scope) {
            scope.openFeedbackForm = function () {
                scope.$emit('OPEN_MODAL_FEEDBACK');
            };

            scope.model = {
                facebookModel: {
                    fbIconImage: config.api.hosts.BACKEND + '/resources/images/dashboard/shareicon_facebook_mobile.png',
                    fbIconImageHeight: '60',
                    fbIconImageWidth: '58',
                    fbUrlShare: config.social.facebookUrlShare,
                    fbImagePresentation: 'http://itsallfreeonline.com/wp-content/uploads/2012/10/Sears-Shop-Your-Way.png',
                    fbTitlePresentation: 'Local Shop Your Way',
                    fbSubTitlePresentation: 'Shop your way local!!!! don\'t lose it!!'
                },
                twitterModel: {
                    tweet: 'Shopping better with Shop Your Way!',
                    tweetUrl: config.social.twitterkUrlShare,
                    tweetIcon: config.api.hosts.BACKEND + '/resources/images/dashboard/shareicon_twitter_mobile.png'
                }
            };

        }
    };
});