package com.searshc.mygarage.apis.qbank;

import com.searshc.mygarage.entities.QBankAnswer;
import com.searshc.mygarage.entities.QBankQuestion;
import com.searshc.mygarage.exceptions.QBankApiException;

public interface QBankApi {
	QBankQuestion getSingleQuestion(String memberNumber) throws QBankApiException;
	void answerSingleQuestion(String memberNumber, QBankAnswer qBankAnswer);
}