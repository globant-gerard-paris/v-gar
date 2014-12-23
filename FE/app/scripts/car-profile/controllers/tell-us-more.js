'use strict';

angular.module('CarProfile').controller('TellUsMoreCtrl', function ($scope, TellUsMoreSrv, $modal) {
	$scope.showTellUsMore = function() {
		$modal.open({
			templateUrl: 'scripts/car-profile/views/tell-us-more-dlg.html',
			controller: 'TellUsMoreModalCtrl',
			size: 'lg'
		});
	};
}).controller('TellUsMoreModalCtrl', function ($filter, $scope, $modalInstance, TellUsMoreSrv) {
	$scope.rdo = 'SingleSelect';
	$scope.chk = 'MultiChoice';
	$scope.txt = 'FreeText';
	
	$scope.selectedAnswers = [];
	$scope.freeTextAnswer = '';
	$scope.loadingData = true;

	$scope.getData = function() {
		TellUsMoreSrv.getQuestion(function (response) {
			if (response.data !== '') {
				$scope.questionData = response.data;
			} else {
				$scope.questionData = null;
			}
			if ($scope.questionData !== null && $scope.questionData.answerTemplate === $scope.rdo) {
				$scope.selectedAnswers = [ $scope.questionData.choices[0].answerChoiceID ];
			}
			$scope.loadingData = false;
		}, function (response) {
			$scope.questionData = null;
			$scope.loadingData = false;
			console.log('ERROR: ' + response);
		});
	};
	
	$scope.getData();
	
	$scope.ok = function () {
		var qData = $scope.questionData;
		var json = {
				promptGroupName: qData.promptGroupName,
				attributeID : qData.attributeID,
				questionPackageID: qData.questionPackageID,
				questionRuleID: qData.questionRuleID,
				questionTextID: qData.questionTextID,
				answerChoices: $filter('filter')(qData.choices, {answerChoiceID: $scope.selectedAnswers}),
				answerTemplate: qData.answerTemplate,
				freeText: $scope.freeTextAnswer,
				followupAnswerID: '',
				followupAttributeID: '',
				followupQuestionTextID: '',
				followupAnswerChoiceID: ''
			};

		if (qData.answerTemplate === $scope.rdo) {
			if (qData.followupQuestion !== null && qData.followupQuestion.length > 0) {
				var selRec = $filter('filter')(qData.choices, {answerChoiceID: $scope.selectedAnswers})[0];
				if (selRec !== null && selRec.hasFollowup) {
					json.followupAnswerID = selRec.anwerID;
					json.followupAttributeID = qData.attributeID;
					json.followupQuestionTextID = selRec.followupQuestionTextID;
					json.followupAnswerChoiceID = selRec.answerChoiceID;
				}
			}
		}
		
		TellUsMoreSrv.answerQuestion(json, function () {
		}, function (response) {
			console.log('ERROR: ' + response);
		});
		
		$scope.selectedAnswers = [];
		$scope.questionData = null;
		$scope.freeTextAnswer = '';
		$scope.getData();
	};

	$scope.isSubmitDisabled = function() {
		var qData = $scope.questionData;
		if (qData === null || qData === undefined) {
			return true;
		}
		switch(qData.answerTemplate) {
		case $scope.rdo:
			var selRec = $filter('filter')(qData.choices, {answerChoiceID: $scope.selectedAnswers})[0];
			if (selRec !== null && selRec.hasFollowup && $scope.freeTextAnswer.trim() === '') {
				return true;
			}
			return false;
		case $scope.chk:
			if ($scope.selectedAnswers.length > 0) {
				return false;
			}
			return true;
		case $scope.txt:
			return !$scope.questionBankForm.$valid;
		}
		return false;
	};
	
	$scope.isLoadingData = function() {
		return $scope.loadingData;
	};
	
	$scope.hasQuestion = function() {
		return $scope.questionData !== null;
	};
	
	$scope.requiresFollowup = function() {
		var qData = $scope.questionData;
		if (qData !== null && qData !== undefined && qData.answerTemplate === $scope.rdo) {
			var selRec = $filter('filter')(qData.choices, {answerChoiceID: $scope.selectedAnswers})[0];
			if (selRec !== null && selRec.hasFollowup && selRec.answerChoiceID === selRec.answerID) {
				return true;
			}
		}
		return false;
	};
	
	$scope.cancel = function () {
		$modalInstance.close();
	};
	
	$scope.toggleMultiChoiceAnswer = function toggleMultiChoiceAnswer(id) {
		var idx = $scope.selectedAnswers.indexOf(id);
		if (idx > -1) {
			$scope.selectedAnswers.splice(idx, 1);
		} else {
			$scope.selectedAnswers.push(id);
		}
	};
});