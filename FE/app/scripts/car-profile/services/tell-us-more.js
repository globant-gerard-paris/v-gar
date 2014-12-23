'use strict';

angular.module('CarProfile').service('TellUsMoreSrv', function (ApiHttpSrv, config, SessionDataSrv) {
	var getQuestion = function (successCallback, failedCallback) {
		ApiHttpSrv.createHttp('POST', config.api.hosts.BACKEND + '/qbank/' + SessionDataSrv.getCurrentUser() + '/question').then(successCallback, failedCallback); // TODO: Need to uncomment this line
		//ApiHttpSrv.createHttp('POST', 'http://localhost:8080/qbank/3/question').then(successCallback, failedCallback); //7081430000024543
	};

	var answerQuestion = function (record) {
		return ApiHttpSrv.createHttp('POST', config.api.hosts.BACKEND + '/qbank/' + SessionDataSrv.getCurrentUser() + '/answer', JSON.stringify(record));
		//return ApiHttpSrv.createHttp('POST', 'http://localhost:8080/qbank/3/answer', JSON.stringify(record));
	};

	return {
		getQuestion: getQuestion,
		answerQuestion: answerQuestion
	};
});