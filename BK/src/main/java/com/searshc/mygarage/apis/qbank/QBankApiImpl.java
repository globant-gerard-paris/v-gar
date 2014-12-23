package com.searshc.mygarage.apis.qbank;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.mina.proxy.handlers.http.HttpProxyConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.searshc.mygarage.apis.qbank.models.*;
import com.searshc.mygarage.controllers.qbank.QBankController;
import com.searshc.mygarage.entities.QBankAnswer;
import com.searshc.mygarage.entities.QBankAnswerChoice;
import com.searshc.mygarage.entities.QBankQuestion;
import com.searshc.mygarage.entities.QBankQuestionChoice;
import com.searshc.mygarage.exceptions.NCDBApiException;
import com.searshc.mygarage.exceptions.QBankApiException;

@Component
public class QBankApiImpl implements QBankApi {
    private static final Log log = LogFactory.getLog(QBankApiImpl.class);

    @Value("${qbank.api.service.endpoint}")
    private String qBankApiServiceUrl;

    @Value("${qbank.api.service.questionapi.messageVersion}")
    private String messageVersion;
    
    @Value("${qbank.api.service.questionapi.requestorID}")
    private String requestorID;
    
    @Value("${qbank.api.service.questionapi.maxPOSPrompt}")
    private String maxPOSPrompt;
    
    @Value("${qbank.api.service.questionapi.promptGroupName}")
    private String promptGroupName;

    @Override
    public QBankQuestion getSingleQuestion(String memberNumber) throws QBankApiException {
		Validate.isTrue(!StringUtils.isEmpty(memberNumber), "The Member Number cannot be null or empty");
    	//if (memberNumber == null || memberNumber == "") return null;

    	QuestionRequestModel request = new QuestionRequestModel();
    	request.setMemberNumber(memberNumber);
    	request.setMessageVersion(messageVersion);
    	request.setRequestorID(requestorID);

    	QuestionPromptGroupModel promptGroup = new QuestionPromptGroupModel();
    	promptGroup.setMaxPOSPrompt(this.maxPOSPrompt);
    	promptGroup.setPromptGroupName(this.promptGroupName);
    	request.AddPromptGroup(promptGroup);

    	QuestionResponseModel resp = null;
    	try {
    		resp = getSoapResponse(this.qBankApiServiceUrl, request, QuestionResponseModel.class);
    	} catch (Exception exc) {
    		String message = "Question Bank Service failed for memberNumber " + memberNumber;
    		log.error(message, exc);
    		throw new QBankApiException(message);
    	}

    	log.info(resp.getMemberPrompts().getEpsilonResponse().getAdditionalInfo().getStatusText());
    	return getQuestionFromResponse(resp);
    }
    
    private QBankQuestion getQuestionFromResponse(QuestionResponseModel resp) {
    	if (resp == null 
    			|| resp.getMemberPrompts() == null
    			|| resp.getMemberPrompts().getEpsilonResponse() == null
    			|| resp.getMemberPrompts().getEpsilonResponse().getMemberPromptsResponse() == null
    			|| resp.getMemberPrompts().getEpsilonResponse().getMemberPromptsResponse().getMemberPromptsReply() == null
    			|| resp.getMemberPrompts().getEpsilonResponse().getMemberPromptsResponse().getMemberPromptsReply().getCheckoutPrompt() == null
    			|| resp.getMemberPrompts().getEpsilonResponse().getMemberPromptsResponse().getMemberPromptsReply().getCheckoutPrompt().getCheckoutPromptQuestions() == null
    			|| resp.getMemberPrompts().getEpsilonResponse().getMemberPromptsResponse().getMemberPromptsReply().getCheckoutPrompt().getCheckoutPromptQuestions().size() == 0)
    		return null;
    	
        //return resp.getMemberPrompts().getEpsilonResponse().getMemberPromptsResponse().getMemberPromptsReply().getCheckoutPrompt().getCheckoutPromptQuestions().get(0);
        
        CheckoutPromptQuestionsModel srcQuestion = resp.getMemberPrompts().getEpsilonResponse().getMemberPromptsResponse().getMemberPromptsReply().getCheckoutPrompt().getCheckoutPromptQuestions().get(0);
        
        QBankQuestion qBankQuestion = new QBankQuestion();
        qBankQuestion.setAnswerTemplate(srcQuestion.getAnswerTemplate());
        qBankQuestion.setAttributeID(srcQuestion.getAttributeID());
        qBankQuestion.setPromptGroupName(srcQuestion.getPromptGroupName());
        qBankQuestion.setQuestionPackageID(srcQuestion.getQuestionPackageID());
        qBankQuestion.setQuestionText(srcQuestion.getQuestionLine1() + " " + srcQuestion.getQuestionLine2() + " " + srcQuestion.getQuestionLine3() + " " + srcQuestion.getQuestionLine4() + " " + srcQuestion.getQuestionLine5());
        qBankQuestion.setQuestionTextID(srcQuestion.getQuestionTextID());
        qBankQuestion.setQuestionRuleID(srcQuestion.getQuestionRuleID());
        
        for(AnswerOptionModel src : srcQuestion.getAnswerChoices()) {
        	QBankQuestionChoice choice = new QBankQuestionChoice();
        	choice.setAnswerChoiceID(src.getAnswerChoiceID());
        	choice.setAnswerTxt(src.getAnswerTxt());
        	choice.setHasFollowup(false);
        	
        	if (srcQuestion.getFollowupQuestions() != null 
        			&& srcQuestion.getFollowupQuestions().size() > 0) {
        		for(FollowupAnswerModel src2 : srcQuestion.getFollowupQuestions().get(0).getAnswersToFollowup()) {
        			if (choice.getAnswerChoiceID().equals(src2.getAnswerID())) {
        				choice.setHasFollowup(true);
        				choice.setAnswerID(src2.getAnswerID());
        			}
        		}
        		
        		for(FollowupAnswerChoiceModel src2 : srcQuestion.getFollowupQuestions().get(0).getFollowupAnswerChoices()) {
        			choice.setFollowupAnswerID(src2.getFollowupAnswerID());
        		}
        		
        		choice.setFollowupQuestionTextID(srcQuestion.getFollowupQuestions().get(0).getFollowupQuestionTextID());
        		qBankQuestion.setFollowupQuestion(srcQuestion.getFollowupQuestions().get(0).getFollowupQuestionLine1()
        											+ " " + srcQuestion.getFollowupQuestions().get(0).getFollowupQuestionLine2()
        											+ " " + srcQuestion.getFollowupQuestions().get(0).getFollowupQuestionLine3()
        											+ " " + srcQuestion.getFollowupQuestions().get(0).getFollowupQuestionLine4()
        											+ " " + srcQuestion.getFollowupQuestions().get(0).getFollowupQuestionLine5());
        	}
        	qBankQuestion.addChoice(choice);
        }
        
        return qBankQuestion;
    }
    
    @Override
    public void answerSingleQuestion(String memberNumber, QBankAnswer qBankAnswer) {
    	POSPromptQuestionModel mdl = new POSPromptQuestionModel();
    	mdl.setPromptGroupName(qBankAnswer.getPromptGroupName());
    	mdl.setAttributeID(qBankAnswer.getAttributeID());
    	mdl.setQuestionTextID(qBankAnswer.getQuestionTextID());
    	mdl.setQuestionPackageID(qBankAnswer.getQuestionPackageID());
    	mdl.setQuestionRuleID(qBankAnswer.getQuestionRuleID());
    	
    	for(QBankAnswerChoice src : qBankAnswer.getAnswerChoices()) {
    		AnswerChoiceModel answerChoice = new AnswerChoiceModel();
    		answerChoice.setAnswerChoiceID(src.getAnswerChoiceID());
    		answerChoice.setAnswerTxt(src.getAnswerTxt());
    		mdl.addAnswerChoice(answerChoice);
    	}
    	
    	if (qBankAnswer.getAnswerTemplate().equals("SingleSelect")) {
   			if (qBankAnswer.getFreeText() == null)
   				qBankAnswer.setFreeText("");

   			if (qBankAnswer.getFreeText() != null && qBankAnswer.getFreeText().trim().length() > 100)
   				qBankAnswer.setFreeText(qBankAnswer.getFreeText().trim().substring(0, 100));

   			if (qBankAnswer.getFreeText().equals("")) {
   	   			FollowupQuestionResponseModel followupQuestionResponseModel = new FollowupQuestionResponseModel();
   	   			mdl.addFollowupQuestion(followupQuestionResponseModel);   
   			} else {
   				AnswerChoiceModel answerChoiceModel = new AnswerChoiceModel();
   				answerChoiceModel.setAnswerChoiceID(qBankAnswer.getFollowupAnswerID() == null ? "" : qBankAnswer.getFollowupAnswerID());
   				answerChoiceModel.setAnswerTxt(qBankAnswer.getFreeText());
    		
   				FollowupQuestionResponseModel followupQuestionResponseModel = new FollowupQuestionResponseModel();
   				followupQuestionResponseModel.addAnswerChoice(answerChoiceModel);
   				
   				followupQuestionResponseModel.setFollowupAttributeID(qBankAnswer.getFollowupAttributeID());
   				followupQuestionResponseModel.setFollowupQuestionTextID(qBankAnswer.getFollowupQuestionTextID());

   				mdl.addFollowupQuestion(followupQuestionResponseModel);
   			}
    	}
    	
    	List<POSPromptQuestionModel> posPromptQuestionList = new ArrayList<POSPromptQuestionModel>();
    	posPromptQuestionList.add(mdl);
    	
    	SaveAnswerRequestModel request = new SaveAnswerRequestModel();
    	request.setMemberNumber(memberNumber);
    	request.setMessageVersion(messageVersion);
    	request.setRequestorID(requestorID);
    	request.setPromptQuestions(posPromptQuestionList);

    	SaveAnswerResponseModel response = getSoapResponse(this.qBankApiServiceUrl, request, SaveAnswerResponseModel.class);
    	
    	Boolean isSuccessful = false;
    	try {
    		isSuccessful = response.getMemberPromptsModel().getEpsilonResponse().getAdditionalInfo().getStatusText().equals("Success");
    		log.info("Is Call Successful " + isSuccessful);
    	} catch (Exception exc) {
    		log.info("Call Errored out " + isSuccessful);
    	}
	}
    
    private <T> T getSoapResponse(String url, Object input, Class<T> outputType) {
    	Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    	marshaller.setClassesToBeBound(new Class<?>[] { input.getClass(), outputType });

    	SaajSoapMessageFactory factory = new SaajSoapMessageFactory();
    	factory.afterPropertiesSet();
    	
    	WebServiceTemplate template = new WebServiceTemplate(factory);
    	template.setMarshaller(marshaller);
    	template.setUnmarshaller(marshaller);
    	template.setMessageSender(new HttpComponentsMessageSender());
    	return outputType.cast(template.marshalSendAndReceive(url, input));
    }
}