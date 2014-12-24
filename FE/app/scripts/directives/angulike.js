'use strict';
/*jshint -W098, -W003, -W004*/
(function () {
    angular.module('angulike', [])

        .directive('fbShare', [
            '$window', '$rootScope', function ($window, $rootScope) {
                return {
                    restrict: 'E',
                    scope: {
                        model: '=?'
                    },
                    link: function (scope, element, attrs) {
                        if (!$window.FB) {
                            // Load Facebook SDK if not already loaded
                            $.getScript('//connect.facebook.net/en_US/sdk.js', function () {
                                $window.FB.init({
                                    appId: $rootScope.facebookAppId,
                                    xfbml: true,
                                    version: 'v2.0'
                                });
                                renderLikeButton();
                            });
                        } else {
                            renderLikeButton();
                        }

                        var watchAdded = false;

                        function renderLikeButton() {
                            if (!!attrs.model && !scope.model && !watchAdded) {
                                // wait for data if it hasn't loaded yet
                                var watchAdded = true;
                                var unbindWatch = scope.$watch('model', function (newValue, oldValue) {
                                    if (newValue) {
                                        renderLikeButton();

                                        // only need to run once
                                        unbindWatch();
                                    }

                                });
                                return;
                            } else {
                                element.html('<a target="_blank" href="http://www.facebook.com/sharer/sharer.php?s=100&amp;p[url]=' + scope.model.fbUrlShare + '&amp;p[images][0]=' + scope.model.fbImagePresentation + '&amp;p[title]=' + scope.model.fbTitlePresentation + '&amp;p[summary]="' + scope.model.fbSubTitlePresentation + '" target="_blank"><img alt="" src="' + scope.model.fbIconImage + '" width="' + scope.model.fbIconImageWidth + '" height="' + scope.model.fbIconImageHeight + '"></a>');
                                $window.FB.XFBML.parse(element.parent()[0]);
                            }
                        }
                    }
                };
            }
        ])

        .directive('tweet', [
            '$window', function ($window) {
                return {
                    restrict: 'E',
                    scope: {
                        model: '='
                    },
                    link: function (scope, element, attrs) {
                        if (!$window.twttr) {
                            // Load Twitter SDK if not already loaded
                            $.getScript('//platform.twitter.com/widgets.js', function () {
                                renderTweetButton();
                            });
                        } else {
                            renderTweetButton();
                        }

                        var watchAdded = false;

                        function renderTweetButton() {
                            if (!scope.model.tweet && !watchAdded) {
                                // wait for data if it hasn't loaded yet
                                watchAdded = true;
                                var unbindWatch = scope.$watch('model.tweet', function (newValue, oldValue) {
                                    if (newValue) {
                                        renderTweetButton();

                                        // only need to run once
                                        unbindWatch();
                                    }
                                });
                                return;
                            } else {
                                element.html('<a target="_blank" href="https://twitter.com/intent/tweet?url=' + scope.model.tweetUrl + '&amp;text=' + scope.model.tweet + '"><img src="' + scope.model.tweetIcon + '"></a>');
                                $window.twttr.widgets.load(element.parent()[0]);
                            }
                        }
                    }
                };
            }
        ]);
})();