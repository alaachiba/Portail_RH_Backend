package com.smartup.p_rh.mail;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.smartup.p_rh.users.User;
import com.smartup.p_rh.users.UserServiceImp;

@RestController
@RequestMapping("/rest/api")
public class MailContoller {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(MailContoller.class);
	
	@Autowired
	private UserServiceImp userService;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@PutMapping("/sendEmailToUser")
	public ResponseEntity<Boolean> sendMailToUser(@RequestBody Mail mailDto,
			UriComponentsBuilder uriComponentBuilder) {

		User user = userService.findUserById(mailDto.getIdUser());
		if (user == null) {
			String errorMessage = "The selected user for sending email is not found in the database";
			LOGGER.info(errorMessage);
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		} else if (user != null && StringUtils.isEmpty(user.getEmail())) {
			String errorMessage = "No existing email for the selected user for sending email to";
			LOGGER.info(errorMessage);
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("mailspring3@gmail.com");
		mail.setTo(user.getEmail());
		System.out.println(user.getEmail());
		mail.setSentDate(new Date());
		mail.setSubject(mailDto.getEmailSubject());
		mail.setText(mailDto.getEmailContent(user.getNom(),user.getPwd()));
		System.out.println(mail.toString());

		try {
			javaMailSender.send(mail);
		} catch (MailException e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

}
