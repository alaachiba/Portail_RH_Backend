package com.smartup.p_rh.controller;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartup.p_rh.message.request.LoginForm;
import com.smartup.p_rh.message.request.SignUpForm;
import com.smartup.p_rh.message.response.JwtResponse;
import com.smartup.p_rh.message.response.ResponseMessage;
import com.smartup.p_rh.role.Role;
import com.smartup.p_rh.role.RoleName;
import com.smartup.p_rh.role.RoleRepository;
import com.smartup.p_rh.security.jwt.JwtProvider;
import com.smartup.p_rh.users.User;
import com.smartup.p_rh.users.UserDTO;
import com.smartup.p_rh.users.UserRepository;
import com.smartup.p_rh.users.UserServiceImp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController

@RequestMapping("/api/auth")
@Api(value = "Rest Controller: Authentification")

public class AuthRestAPIs {
	@Autowired
	private UserServiceImp userService;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPwd()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}
	public void mailling(String mail, String message) {
		final String username = "smartup.hr.3s@gmail.com";
		final String password = "159753Aa";
		String fromEmail = "smartup.hr.3s@gmail.com";
		Properties properties = new Properties();

		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.user", username);
		properties.setProperty("mail.smtp.password", password);
		properties.setProperty("mail.smtp.localhost", "smtp.gmail.com");
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
			msg.setSubject("Mot de passe oublié !");
			Multipart emailContent = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(message);
			emailContent.addBodyPart(textBodyPart);
			msg.setContent(emailContent);
			Transport.send(msg);
			System.out.println("Sent message");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	@PutMapping("/resetpassword")
	@ApiOperation(value = "Mot de passe oublié", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "L'adresse email n'existe pas n'existe pas"),
			@ApiResponse(code = 200, message = "Le mail de mot de passe oublié a été envoyé avec succés"),
			@ApiResponse(code = 304, message = "Erreur lors de l'envoi") })
	public ResponseEntity<UserDTO> resetpassword(@RequestBody String email) {
		if (!userService.existsUserByEmail(email)) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		System.out.println(email);
		userService.passwordRequestUser(true, email);
		mailling("alaa.chiba@esprit.tn", "L'utilisateur de l'adresse mail : "+email+ " a oublié son mot de passe");
		return new ResponseEntity<UserDTO>(HttpStatus.OK);

	}
	@PostMapping("/signup")
	@ApiResponse(code = 201, message = "L'utilisateur a été créé avec succées")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		User user = new User(signUpRequest.getNom(), signUpRequest.getPrenom(), signUpRequest.getMatricule(),
				signUpRequest.getEmail(), signUpRequest.getStatus(), encoder.encode(signUpRequest.getPwd()),
				signUpRequest.getEmail());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByLibelle(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);

				break;
			case "pm":
				Role pmRole = roleRepository.findByLibelle(RoleName.ROLE_MANAGER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(pmRole);

				break;
			default:
				Role userRole = roleRepository.findByLibelle(RoleName.ROLE_EMPLOYE)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		userRepository.save(user);
		mailling(signUpRequest.getEmail(), "Bienvenu "+signUpRequest.getNom()+ ". Votre adresse mail : "+signUpRequest.getEmail()
		+" et votre mot de passe : "+signUpRequest.getPwd()+" veuillez connecté et la changer");

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}

	private UserDTO mapUserToUserDTO(User user) {
		ModelMapper mapper = new ModelMapper();
		UserDTO userDTO = mapper.map(user, UserDTO.class);
		return userDTO;
	}

	private User mapUserDTOToUser(UserDTO userDTORequest) {
		ModelMapper mapper = new ModelMapper();
		User user = mapper.map(userDTORequest, User.class);
		return user;
	}

}
