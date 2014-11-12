'use strict';
module.exports = function (grunt) {
    grunt.config('htmllint', {
        all: [
            '<%= grunt.config.app %>/scripts/**/views/*.html',
            '<%= grunt.config.app %>/*.html'
        ],
        options: {
            ignore: [

                // Relax following general errors not necessary to valid
                'Start tag seen without seeing a doctype first. Expected “<!DOCTYPE html>”.',
                'XHTML element “head” is missing a required instance of child element “title”.',
                'Attribute “ng-include” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-controller” not allowed on XHTML element “body” at this point.',
                'Attribute “ng-view” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-app” not allowed on XHTML element “html” at this point.',
                'Bad value “X-UA-Compatible” for attribute “http-equiv” on XHTML element “meta”.',

                // Relaxing Angular directives - DIV element.
                'Attribute “ng-bind” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-bindhtml” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-bindtemplate” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-blur” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-change” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-checked” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-class” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-classeven” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-classodd” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-click” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-cloak” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-controller” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-copy” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-csp” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-cut” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-dblclick” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-disabled” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-focus” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-form” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-hide” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-href” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-if” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-include” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-init” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-keydown” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-keypress” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-keyup” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-list” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-model” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-modelOptions” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-mousedown” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-mouseenter” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-mouseleave” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-mousemove” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-mouseover” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-mouseup” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-nonbindable” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-open” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-paste” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-pluralize” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-readonly” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-repeat” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-selected” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-show” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-src” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-srcset” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-style” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-submit” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-switch” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-transclude” not allowed on XHTML element “div” at this point.',
                'Attribute “ng-value” not allowed on XHTML element “div” at this point.',

                // Relaxing  Angular directives - SPAN element.
                'Attribute “ng-bind” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-bindhtml” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-Bindtemplate” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-blur” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-change” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-checked” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-class” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-classeven” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-classodd” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-click” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-cloak” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-controller” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-copy” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-csp” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-cut” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-dblclick” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-disabled” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-focus” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-form” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-hide” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-href” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-if” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-include” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-init” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-keydown” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-keypress” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-keyup” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-list” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-model” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-modelOptions” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-mousedown” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-mouseenter” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-mouseleave” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-mousemove” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-mouseover” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-mouseup” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-nonbindable” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-open” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-paste” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-pluralize” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-readonly” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-repeat” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-selected” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-show” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-src” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-srcset” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-style” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-submit” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-switch” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-transclude” not allowed on XHTML element “span” at this point.',
                'Attribute “ng-value” not allowed on XHTML element “span” at this point.',

                // Relaxing  Angular directives - A element.'Attribute “ng-Bind” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-Bindhtml” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-bindtemplate” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-blur” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-change” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-checked” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-class” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-classeven” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-classodd” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-click” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-cloak” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-controller” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-copy” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-csp” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-cut” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-dblclick” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-disabled” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-focus” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-form” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-hide” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-href” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-if” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-include” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-init” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-keydown” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-keypress” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-keyup” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-list” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-model” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-modelOptions” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-mousedown” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-mouseenter” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-mouseleave” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-mousemove” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-mouseover” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-mouseup” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-nonbindable” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-open” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-paste” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-pluralize” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-readonly” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-repeat” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-selected” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-show” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-src” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-srcset” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-style” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-submit” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-switch” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-transclude” not allowed on XHTML element “a” at this point.',
                'Attribute “ng-value” not allowed on XHTML element “a” at this point.',

                // Relaxing  Angular directives - button element.
                'Attribute “ng-bind” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-bindhtml” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-bindtemplate” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-blur” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-change” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-checked” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-class” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-classeven” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-classodd” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-click” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-cloak” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-controller” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-copy” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-csp” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-cut” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-dblclick” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-disabled” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-focus” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-form” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-hide” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-href” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-if” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-include” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-init” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-keydown” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-keypress” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-keyup” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-list” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-model” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-modelOptions” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-mousedown” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-mouseenter” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-mouseleave” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-mousemove” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-mouseover” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-mouseup” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-nonbindable” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-open” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-paste” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-pluralize” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-readonly” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-repeat” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-selected” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-show” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-src” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-srcset” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-style” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-submit” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-switch” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-transclude” not allowed on XHTML element “button” at this point.',
                'Attribute “ng-value” not allowed on XHTML element “button” at this point.',

                // Relaxing  Angular directives - input element.
                'Attribute “ng-bind” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-bindhtml” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-bindtemplate” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-blur” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-change” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-checked” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-class” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-classeven” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-classodd” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-click” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-cloak” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-controller” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-copy” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-csp” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-cut” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-dblclick” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-disabled” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-focus” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-form” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-hide” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-href” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-if” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-include” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-init” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-keydown” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-keypress” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-keyup” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-list” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-model” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-modelOptions” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-mousedown” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-mouseenter” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-mouseleave” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-mousemove” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-mouseover” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-mouseup” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-nonbindable” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-open” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-paste” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-pluralize” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-readonly” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-repeat” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-selected” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-show” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-src” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-srcset” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-style” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-submit” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-switch” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-transclude” not allowed on XHTML element “input” at this point.',
                'Attribute “ng-value” not allowed on XHTML element “input” at this point.',

                // Relaxing  Angular directives - form element.
                'Attribute “ng-bind” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-bindhtml” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-bindtemplate” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-blur” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-change” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-checked” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-class” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-classeven” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-classodd” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-click” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-cloak” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-controller” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-copy” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-csp” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-cut” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-dblclick” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-disabled” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-focus” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-form” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-hide” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-href” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-if” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-include” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-init” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-keydown” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-keypress” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-keyup” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-list” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-model” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-modelOptions” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-mousedown” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-mouseenter” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-mouseleave” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-mousemove” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-mouseover” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-mouseup” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-nonbindable” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-open” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-paste” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-pluralize” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-readonly” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-repeat” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-selected” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-show” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-src” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-srcset” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-style” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-submit” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-switch” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-transclude” not allowed on XHTML element “form” at this point.',
                'Attribute “ng-value” not allowed on XHTML element “form” at this point.',

                'XHTML element “store-row” not allowed as child of XHTML element “div” in this context. (Suppressing further errors from this subtree.)',
                'XHTML element “ui-gmap-google-map” not allowed as child of XHTML element “div” in this context. (Suppressing further errors from this subtree.)',
                'XHTML element “address” not allowed as child of XHTML element “h5” in this context. (Suppressing further errors from this subtree.)',
                'XHTML element “ui-gmap-google-map” not allowed as child of XHTML element “a” in this context. (Suppressing further errors from this subtree.)',
                'Attribute “collapse” not allowed on XHTML element “div” at this point.',
                'XHTML element “recall-information” not allowed as child of XHTML element “div” in this context. (Suppressing further errors from this subtree.)',
                'Attribute “ng-click” not allowed on XHTML element “img” at this point.',
                'XHTML element “record-row” not allowed as child of XHTML element “div” in this context. (Suppressing further errors from this subtree.)',
                'Attribute “ng-minlength” not allowed on XHTML element “input” at this point.'
            ]
        }
    });
};