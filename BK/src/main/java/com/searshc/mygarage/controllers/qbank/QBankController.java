package com.searshc.mygarage.controllers.qbank;

import static org.apache.commons.lang3.Validate.notNull;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;

import org.apache.commons.lang3.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.Marshaller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import scala.Console;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.searshc.mygarage.apis.qbank.models.*;
import com.searshc.mygarage.dtos.VehicleConfirmationDTO;
import com.searshc.mygarage.entities.QBankAnswer;
import com.searshc.mygarage.entities.QBankQuestion;
import com.searshc.mygarage.entities.User;
import com.searshc.mygarage.exceptions.QBankApiException;
import com.searshc.mygarage.services.qbank.QBankService;
import com.searshc.mygarage.services.user.UserService;

@RestController
@RequestMapping("/qbank")
public class QBankController {
	private static final Log log = LogFactory.getLog(QBankController.class);

	private QBankService qBankService;
	private ObjectMapper objectMapper;
	private UserService userService;

	@Inject
	public QBankController(final QBankService qBankService, ObjectMapper objectMapper, UserService userService) {
		this.qBankService = notNull(qBankService, "The QBank Service cannot be null");
		this.objectMapper = notNull(objectMapper, "ObjectMapper parameter cannot be null");
		this.userService = Validate.notNull(userService, "The User Service cannot be null");
	}

	@RequestMapping(value = "/{userId}/question", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<QBankQuestion> getSingleQuestion(@PathVariable("userId") long userId) throws QBankApiException {
		User user = this.userService.getItem(userId);
		String memberNumber = user.getSywrMemberNumber();
		
		//String memberNumber = "7081440000273826";
		return new ResponseEntity<QBankQuestion>(qBankService.getSingleQuestion(memberNumber), null, HttpStatus.OK);
	}

	@RequestMapping(value = "/{userId}/answer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> answerSingleQuestion(@PathVariable("userId") long userId, @RequestBody String jsonBody) throws Exception {
		User user = this.userService.getItem(userId);
		String memberNumber = user.getSywrMemberNumber();

		//String memberNumber = "7081440000273826";
		QBankAnswer qBankAnswer = objectMapper.readValue(jsonBody, QBankAnswer.class);
		qBankService.answerSingleQuestion(memberNumber, qBankAnswer);
		return new ResponseEntity<String>("Ok", null, HttpStatus.OK);
	}
}