package com.smartup.p_rh.demandefraispro;

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
@RequestMapping("/rest/api/fraisDem")
@CrossOrigin(origins = "*")
@Api(value = "Rest Controller: Demande de frais professionels")
public class DemFraisController {

	@Autowired
	DemFraisServiceImp demFraisService;

	@GetMapping("/allDemFrais")
	@ApiOperation(value = "Afficher tous les demandes de frais professionels", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des demandes de frais affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucune demande de frais touvé"), })
	public ResponseEntity<List<DemFraisDTO>> getAllDem() {
		List<DemFrais> DemFraiss = demFraisService.getAllDem();
		if (!CollectionUtils.isEmpty(DemFraiss)) {
			DemFraiss.removeAll(Collections.singleton(null));
			List<DemFraisDTO> DemFraisDTOs = DemFraiss.stream().map(DemFrais -> {
				return mapDemFraisToDemFraisDTO(DemFrais);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<DemFraisDTO>>(DemFraisDTOs, HttpStatus.OK);
		}
		return new ResponseEntity<List<DemFraisDTO>>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/addDemFrais")
	@ApiOperation(value = "Ajouter une demande des frais professionels", response = DemFraisDTO.class)
	@ApiResponse(code = 201, message = "La demande de frais a été envoyé avec succées")
	public ResponseEntity<DemFraisDTO> createNewDemFrais(@RequestBody DemFraisDTO demDTORequest) {
		DemFrais demRequest = mapDemFraisDTOToDemFrais(demDTORequest);
		DemFrais DemFrais = demFraisService.saveDem(demRequest);
		DemFraisDTO DemFraisDTO = mapDemFraisToDemFraisDTO(DemFrais);
		return new ResponseEntity<DemFraisDTO>(DemFraisDTO, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteDemFrais/{id}")
	@ApiOperation(value = "Supprimer une demande des frais professionels", response = String.class)
	@ApiResponse(code = 204, message = "La demande de frais a été supprimé avec succées")
	public ResponseEntity<DemFraisDTO> deleteDemFrais(@PathVariable Integer id) {
		demFraisService.deleteDem(id);
		return new ResponseEntity<DemFraisDTO>(HttpStatus.NO_CONTENT);
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
			msg.setSubject("Retour demande des frais professionnelles");
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
	@ApiOperation(value = "Modifier une demande des frais professionnels existante", response = DemFraisDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "La demande des frais professionnels n'existe pas"),
			@ApiResponse(code = 200, message = "La demande des frais professionnel a été modifiée"),
			@ApiResponse(code = 304, message = "Erreur lors de la modification de la demande des frais professionnels") })
	public ResponseEntity<DemFraisDTO> updateDem(@RequestBody DemFraisDTO demDTORequest) {
		DemFrais demRequest = mapDemFraisDTOToDemFrais(demDTORequest);
		if (!demFraisService.checkIfIdExists(demDTORequest.getId())) {
			return new ResponseEntity<DemFraisDTO>(HttpStatus.NOT_FOUND);
		}
		DemFrais demandeFrais = demFraisService.updateDem(demRequest);
		if (demRequest.getStatut().equals("Accepté")) {
			mailling(demRequest.getUser().getEmail(), "Votre demande des frais professionnelles a été accepté. Le montant est: "+ demRequest.getMontant()+ " DT");
		} else if (demRequest.getStatut().equals("Réfusé")) {
			mailling(demRequest.getUser().getEmail(), "Votre demande des frais professionnelles a été refusé. La motif est: " + demRequest.getMotif());
		}
		if (demandeFrais != null) {
			DemFraisDTO demandeDTO = mapDemFraisToDemFraisDTO(demandeFrais);
			return new ResponseEntity<DemFraisDTO>(demandeDTO, HttpStatus.OK);
		}
		return new ResponseEntity<DemFraisDTO>(HttpStatus.NOT_MODIFIED);
	}

	@GetMapping("/myDemandes/{email}")
	@ApiOperation(value = "Afficher tous mes demandes des frais professionnels", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste de mes demandes affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucune demande des frais professionnels touvée"), })
	public ResponseEntity<List<DemFraisDTO>> getAllDemandeByUser(@PathVariable String email) {
		List<DemFrais> demFrais = demFraisService.findByuserEmail(email);
		if (!CollectionUtils.isEmpty(demFrais)) {
			demFrais.removeAll(Collections.singleton(null));
			List<DemFraisDTO> demDtos = demFrais.stream().map(dem -> {
				return mapDemFraisToDemFraisDTO(dem);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<DemFraisDTO>>(demDtos, HttpStatus.OK);
		}
		return new ResponseEntity<List<DemFraisDTO>>(HttpStatus.NO_CONTENT);
	}

	private DemFraisDTO mapDemFraisToDemFraisDTO(DemFrais DemFrais) {
		ModelMapper mapper = new ModelMapper();
		DemFraisDTO DemFraisDTO = mapper.map(DemFrais, DemFraisDTO.class);
		return DemFraisDTO;
	}

	private DemFrais mapDemFraisDTOToDemFrais(DemFraisDTO demDTORequest) {
		ModelMapper mapper = new ModelMapper();
		DemFrais DemFrais = mapper.map(demDTORequest, DemFrais.class);
		return DemFrais;
	}

}
