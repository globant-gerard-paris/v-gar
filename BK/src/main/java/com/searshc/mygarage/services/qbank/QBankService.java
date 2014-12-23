package com.searshc.mygarage.services.qbank;

import com.searshc.mygarage.entities.QBankAnswer;
import com.searshc.mygarage.entities.QBankQuestion;
import com.searshc.mygarage.exceptions.QBankApiException;

public interface QBankService {
	QBankQuestion getSingleQuestion(String memberNumber) throws QBankApiException;
	void answerSingleQuestion(String memberNumber, QBankAnswer qBankAnswer);
}