package com.searshc.mygarage.services.qbank;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import com.searshc.mygarage.apis.qbank.QBankApi;
import com.searshc.mygarage.entities.QBankAnswer;
import com.searshc.mygarage.entities.QBankQuestion;
import com.searshc.mygarage.exceptions.QBankApiException;

@Service
public class QBankServiceImpl implements QBankService {
    private QBankApi qBankApi;

    @Inject
    public QBankServiceImpl(QBankApi qBankApi) {
    	this.qBankApi = Validate.notNull(qBankApi, "The QBank Api cannot be null");
    }

    @Override
    public QBankQuestion getSingleQuestion(String memberNumber) throws QBankApiException {
    	return this.qBankApi.getSingleQuestion(memberNumber);
    }
    
    @Override
    public void answerSingleQuestion(String memberNumber, QBankAnswer qBankAnswer) {
    	this.qBankApi.answerSingleQuestion(memberNumber, qBankAnswer);
    }
}