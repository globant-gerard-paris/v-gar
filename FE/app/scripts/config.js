/* jshint quotmark:true, indent:false, white: false */
"use strict";

 angular.module("AppConfig", [])

.constant("config", {
    "api": {
        "hosts": {
            "DEV": "http://127.0.0.1:9000",
            "BACKEND": "http://127.0.0.1:8080",
            "STAGE": "http://127.0.0.1:8080",
            "PROD": "http://127.0.0.1:8080"
        },
        "methods": {
            "SYW_LOGIN": {
                "use_host": "PROD",
                "host_paths": {
                    "PROD": "/logint/prod"
                }
            }
        }
    }
})

;