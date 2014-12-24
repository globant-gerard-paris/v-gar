/* jshint quotmark:true, indent:false, white: false */
"use strict";

 angular.module("AppConfig", [])

.constant("config", {
    "api": {
        "hosts": {
            "BACKEND": "http://127.0.0.1:8080"
        }
    },
    "extUrl": {
        "appoinment": "http://www.searsauto.com/stores/"
    },
    "social": {
        "twitterkUrlShare": "http://sandbox.shopyourway.com/app/17510/l",
        "facebookUrlShare": "http://sandbox.shopyourway.com/app/17510/l",
        "facebookAppID": "674196112699408"
    }
})

;