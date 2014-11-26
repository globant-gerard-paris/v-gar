'use strict';

angular.module('PresentationFlow').service('LandingSrv', function ($q, SessionDataSrv, ApiHttpSrv, config) {

        var deferGetUserSession;

        var getHomeSessionInfo = function (token) {
            deferGetUserSession = $q.defer();
            ApiHttpSrv.createHttp('GET', config.api.hosts.BACKEND + '/home/user/token/' + token).then(successGetSessionInfo, failGetSessionInfo);
            return deferGetUserSession.promise;
        };

        var successGetSessionInfo = function (response) {
            var statusVehicle = { haveNCDBCars: false, haveLinkedCars: false, haveManualCars: false };
            var vehicles = response.data.vehicleInformation;
            if (vehicles) {
                for (var i = 0; i < vehicles.length; i++) {
                    var vehicle = vehicles[i];
                    statusVehicle = updateStatusReport(statusVehicle, vehicle.status);
                }
                SessionDataSrv.setCachedVehicleUsers(vehicles);
                SessionDataSrv.setCurrentUser(response.data.userId);
            }
            deferGetUserSession.resolve(statusVehicle);
        };

        var updateStatusReport = function (reportStatus, action) {
            if ('NCDB' === action && !reportStatus.haveNCDBCars) {
                reportStatus.haveNCDBCars = true;
                return reportStatus;
            } else if ('MANUAL' === action && !reportStatus.haveManualCars) {
                reportStatus.haveManualCars = true;
                return reportStatus;
            } else if ('LINKED' === action && !reportStatus.haveLinkedCars) {
                reportStatus.haveLinkedCars = true;
                return reportStatus;
            }

            return reportStatus;
        };

        var failGetSessionInfo = function (response) {
            deferGetUserSession.reject(response);
        };

        return {
            getHomeSessionInfo: getHomeSessionInfo
        };

    }
)
;