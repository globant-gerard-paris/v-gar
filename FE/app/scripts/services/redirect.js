'use strict';

angular.module('Services').service('RedirectSrv', function ($location) {

    //stack for saving url history
    var _historyStack = [];

    //save previus url in stack
    var _saveHistory = function (url) {
        //dont add contiguos duplicates
        if (url !== _.last(_historyStack)) {
            // push to stack
            _historyStack.push(url);
        }
    };

    var getHistory = function () {
        return _historyStack;
    };

    // redirect to previous page
    var redirectToPrev = function () {
        //stack to prev url
        var prevToken = _removeLastTokenAndGetPrevToken(_historyStack);
        redirectTo(prevToken, true);
    };

    var _removeLastTokenAndGetPrevToken = function () {
        if (_historyStack.length > 1) {
            _historyStack.pop();
            return _historyStack[_historyStack.length - 1];
        } else {
            return _historyStack[0];
        }
    };

    // redirect to hash
    var redirectTo = function (hash, withoutSaveHistory) {
        if (withoutSaveHistory) {
            _redirectWithoutSaveHistory(hash);
        } else {
            _redirect(hash);
        }
    };

    var _redirectWithoutSaveHistory = function (hash) {
        $location.url(hash);
    };

    var _redirect = function (hash) {
        _saveHistory(hash);
        $location.url(hash);
    };

    var redirectTo404 = function () {
        _saveHistory($location.path());
        redirectTo('/404');
    };

    /**
     * Error page used for unexpected errors (error handling).
     */
    var redirectToErrorPage = function () {
        _saveHistory($location.path());
        redirectTo('/error');
    };

    // return service
    return {
        redirectTo: redirectTo,
        redirectToPrev: redirectToPrev,
        redirectTo404: redirectTo404,
        redirectToErrorPage: redirectToErrorPage,
        getHistory: getHistory
    };

});