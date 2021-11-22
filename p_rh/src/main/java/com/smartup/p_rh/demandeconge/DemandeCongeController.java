package com.smartup.p_rh.demandeconge;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

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

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest/api/congeDem")
@CrossOrigin(origins = "*")
@Api(value = "Rest Controller: Demande de congé")
public class DemandeCongeController {

	@Autowired
	private DemandeCongeServiceImp CongeService;

	@PostMapping("/addDemandeConge")
	@ApiOperation(value = "Ajouter une demande de congé", response = DemandeCongeDTO.class)
	@ApiResponse(code = 201, message = "La demande de congé à été envoyé avec succées")
	public ResponseEntity<DemandeCongeDTO> createNewDemConge(@RequestBody DemandeCongeDTO demDTORequest) {
		DemandeConge demRequest = mapDemandeCongeDTOToDemandeConge(demDTORequest);
		DemandeConge demandeConge = CongeService.saveDemConge(demRequest);
		DemandeCongeDTO demandeCongeDTO = mapDemandeCongeToDemandeCongeDTO(demandeConge);
		return new ResponseEntity<DemandeCongeDTO>(demandeCongeDTO, HttpStatus.CREATED);
	}

	@GetMapping("/allDemandeConge")
	@ApiOperation(value = "Afficher tous les demandes de congé", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des demandes de congé affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucune demande de congé touvé"), })
	public ResponseEntity<List<DemandeCongeDTO>> getAllDem() {
		List<DemandeConge> demandeConges = CongeService.getAllDemConge();
		if (!CollectionUtils.isEmpty(demandeConges)) {
			demandeConges.removeAll(Collections.singleton(null));
			List<DemandeCongeDTO> demandeCongeDTOs = demandeConges.stream().map(demandeConge -> {
				return mapDemandeCongeToDemandeCongeDTO(demandeConge);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<DemandeCongeDTO>>(demandeCongeDTOs, HttpStatus.OK);
		}
		return new ResponseEntity<List<DemandeCongeDTO>>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteDemConge/{id}")
	@ApiOperation(value = "Supprimer une demande de congé s'elle existe", response = String.class)
	@ApiResponse(code = 204, message = "La demande de congé a été supprimé avec succées")
	public ResponseEntity<DemandeCongeDTO> deleteDemConge(@PathVariable Integer id) {
		CongeService.deleteDemConge(id);
		return new ResponseEntity<DemandeCongeDTO>(HttpStatus.NO_CONTENT);
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
			msg.setSubject("Retour demande de congé");
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

	@PutMapping("/updateDem")
	@ApiOperation(value = "Modifier une demande de congé existant", response = DemandeCongeDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "La demande de congé n'existe pas"),
			@ApiResponse(code = 200, message = "La demande de congé a été modifiée"),
			@ApiResponse(code = 304, message = "Erreur lors de la modification de la demande de congé") })
	public ResponseEntity<DemandeCongeDTO> updateDem(@RequestBody DemandeCongeDTO demDTORequest) {
		DemandeConge demRequest = mapDemandeCongeDTOToDemandeConge(demDTORequest);
		if (!CongeService.checkIfIdExists(demDTORequest.getId())) {
			return new ResponseEntity<DemandeCongeDTO>(HttpStatus.NOT_FOUND);
		}
		DemandeConge demandeConge = CongeService.updateDem(demRequest);
		if (demRequest.getStatus().equals("Accepté")) {
			mailling(demRequest.getUser().getEmail(), "Votre demande de congé a été accepté");
		} else if (demRequest.getStatus().equals("Réfusé")) {
			mailling(demRequest.getUser().getEmail(), "Votre demande de congé a été refusé. La motif est" + demRequest.getMotif());
		}
		if (demandeConge != null) {
			DemandeCongeDTO demandeDTO = mapDemandeCongeToDemandeCongeDTO(demandeConge);
			return new ResponseEntity<DemandeCongeDTO>(demandeDTO, HttpStatus.OK);
		}
		return new ResponseEntity<DemandeCongeDTO>(HttpStatus.NOT_MODIFIED);
	}

	@GetMapping("/myDemandes/{email}")
	@ApiOperation(value = "Afficher tous mes demandes de congé", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste de mes demandes affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucune demande de congé touvé"), })
	public ResponseEntity<List<DemandeCongeDTO>> getAllDemandeByUser(@PathVariable String email) {
		List<DemandeConge> demConge = CongeService.findByuserEmail(email);
		if (!CollectionUtils.isEmpty(demConge)) {
			demConge.removeAll(Collections.singleton(null));
			List<DemandeCongeDTO> demDtos = demConge.stream().map(dem -> {
				return mapDemandeCongeToDemandeCongeDTO(dem);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<DemandeCongeDTO>>(demDtos, HttpStatus.OK);
		}
		return new ResponseEntity<List<DemandeCongeDTO>>(HttpStatus.NO_CONTENT);
	}

	private DemandeCongeDTO mapDemandeCongeToDemandeCongeDTO(DemandeConge demandeConge) {
		ModelMapper mapper = new ModelMapper();
		DemandeCongeDTO demandeCongeDTO = mapper.map(demandeConge, DemandeCongeDTO.class);
		return demandeCongeDTO;
	}

	private DemandeConge mapDemandeCongeDTOToDemandeConge(DemandeCongeDTO demDTORequest) {
		ModelMapper mapper = new ModelMapper();
		DemandeConge demandeConge = mapper.map(demDTORequest, DemandeConge.class);
		return demandeConge;
	}

}
