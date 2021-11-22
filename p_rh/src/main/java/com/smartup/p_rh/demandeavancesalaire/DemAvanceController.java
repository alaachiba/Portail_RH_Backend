package com.smartup.p_rh.demandeavancesalaire;

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
@RequestMapping("/rest/api/avanceSalaire")
@CrossOrigin(origins = "*")
@Api(value = "Rest Controller: Demande avance sur salaire")
public class DemAvanceController {

	@Autowired
	private DemAvanceServiceImp demAvanceService;

	@PostMapping("/addDemAvance")
	@ApiOperation(value = "Ajouter une demande d'avance sur salaire", response = DemAvanceDTO.class)
	@ApiResponse(code = 201, message = "La demande d'avance à été envoyé avec succées")
	public ResponseEntity<DemAvanceDTO> createDemAvance(@RequestBody DemAvanceDTO demAvanceDTORequest) {
		DemAvance demAvanceRequest = mapDemAvanceDTOToDemAvance(demAvanceDTORequest);
		DemAvance demAvance = demAvanceService.saveDem(demAvanceRequest);
		if (demAvance != null && demAvance.getId() != null) {
			DemAvanceDTO demAvanceDTO = mapDemAvanceToDemAvanceDTO(demAvance);
			return new ResponseEntity<DemAvanceDTO>(demAvanceDTO, HttpStatus.CREATED);
		}
		return new ResponseEntity<DemAvanceDTO>(HttpStatus.NOT_MODIFIED);
	}

	@GetMapping("/ListDemAvance")
	@ApiOperation(value = "Afficher tous les demandes d'avance sur salaire", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des demandes affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucune demande pour avance sur salaire touvé"), })
	public ResponseEntity<List<DemAvanceDTO>> getAllDemandeAvance() {
		List<DemAvance> demAvances = demAvanceService.getAllDemAvance();
		if (!CollectionUtils.isEmpty(demAvances)) {
			demAvances.removeAll(Collections.singleton(null));
			List<DemAvanceDTO> demAvancesDtos = demAvances.stream().map(demAvance -> {
				return mapDemAvanceToDemAvanceDTO(demAvance);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<DemAvanceDTO>>(demAvancesDtos, HttpStatus.OK);
		}
		return new ResponseEntity<List<DemAvanceDTO>>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteDemAvance/{id}")
	@ApiOperation(value = "Supprimer une demande d'avance s'elle existe", response = String.class)
	@ApiResponse(code = 204, message = "La demande d'avance a été supprimé avec succées")
	public ResponseEntity<DemAvanceDTO> deleteDemAvance(@PathVariable Integer id) {
		demAvanceService.deleteDemAvance(id);
		return new ResponseEntity<DemAvanceDTO>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/myDemandes/{email}")
	@ApiOperation(value = "Afficher tous mes demandes d'avance sur salaire", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste de mes demandes affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucune demande pour avance sur salaire touvé"), })
	public ResponseEntity<List<DemAvanceDTO>> getAllDemandeAvanceByUser(@PathVariable String email) {
		List<DemAvance> demAvances = demAvanceService.findByuserEmail(email);
		if (!CollectionUtils.isEmpty(demAvances)) {
			demAvances.removeAll(Collections.singleton(null));
			List<DemAvanceDTO> demAvancesDtos = demAvances.stream().map(demAvance -> {
				return mapDemAvanceToDemAvanceDTO(demAvance);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<DemAvanceDTO>>(demAvancesDtos, HttpStatus.OK);
		}
		return new ResponseEntity<List<DemAvanceDTO>>(HttpStatus.NO_CONTENT);
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
			msg.setSubject("Retour demande d'avance sur salaire");
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
	@ApiOperation(value = "Modifier une demande d'avance sur salaire existante", response = DemAvanceDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "La demande d'avance sur salaire n'existe pas"),
			@ApiResponse(code = 200, message = "La demande d'avance sur salaire a été modifiée"),
			@ApiResponse(code = 304, message = "Erreur lors de la modification de la demande d'avance sur salaire") })
	public ResponseEntity<DemAvanceDTO> updateDem(@RequestBody DemAvanceDTO demDTORequest) {
		DemAvance demRequest = mapDemAvanceDTOToDemAvance(demDTORequest);
		if (!demAvanceService.checkIfIdExists(demDTORequest.getId())) {
			return new ResponseEntity<DemAvanceDTO>(HttpStatus.NOT_FOUND);
		}
		DemAvance demandeAvance = demAvanceService.updateDem(demRequest);
		if (demRequest.getStatut().equals("Accepté")) {
			mailling(demRequest.getUser().getEmail(), "Votre demande d'avance sur salaire a été accepté. Le montant est : " + demRequest.getMontant() + " DT");
		} else if (demRequest.getStatut().equals("Réfusé")) {
			mailling(demRequest.getUser().getEmail(), "Votre demande d'avance sur salaire a été refusé. La motif est : " + demRequest.getMotif());
		}
		if (demandeAvance != null) {
			DemAvanceDTO demandeDTO = mapDemAvanceToDemAvanceDTO(demandeAvance);
			return new ResponseEntity<DemAvanceDTO>(demandeDTO, HttpStatus.OK);
		}
		return new ResponseEntity<DemAvanceDTO>(HttpStatus.NOT_MODIFIED);
	}

	private DemAvanceDTO mapDemAvanceToDemAvanceDTO(DemAvance demAvance) {
		ModelMapper mapper = new ModelMapper();
		DemAvanceDTO demAvanceDTO = mapper.map(demAvance, DemAvanceDTO.class);
		return demAvanceDTO;
	}

	private DemAvance mapDemAvanceDTOToDemAvance(DemAvanceDTO demAvanceDTO) {
		ModelMapper mapper = new ModelMapper();
		DemAvance demAvance = mapper.map(demAvanceDTO, DemAvance.class);
		return demAvance;
	}
}
