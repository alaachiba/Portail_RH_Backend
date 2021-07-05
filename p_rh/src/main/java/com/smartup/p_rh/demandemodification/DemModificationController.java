package com.smartup.p_rh.demandemodification;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
@RequestMapping("/rest/api/modifInfo")
@CrossOrigin(origins = "*")
@Api(value = "Rest Controller: Demande modification des informations personnelles")
public class DemModificationController {

	@Autowired
	private DemModificationServiceImp DemModificationService;

	@PostMapping("/addDemModification")
	@ApiOperation(value = "Ajouter une demande de modification des informations personnelles",
	response = DemModificationDTO.class)
	@ApiResponse(code = 201, message = "La demande de modification a été envoyé avec succées")
	public ResponseEntity<DemModificationDTO> createNewDemModification(
			@RequestBody DemModificationDTO DemModificationDTORequest) {
		DemModification DemModificationRequest = mapDemModificationDTOToDemModification(DemModificationDTORequest);
		DemModification DemModification = DemModificationService.saveDem(DemModificationRequest);
		if (DemModification != null && DemModification.getId() != null) {
			DemModificationDTO DemModificationDTO = mapDemModificationToDemModificationDTO(DemModification);
			return new ResponseEntity<DemModificationDTO>(DemModificationDTO, HttpStatus.CREATED);
		}
		return new ResponseEntity<DemModificationDTO>(HttpStatus.NOT_MODIFIED);
	}


	@GetMapping("/ListDemModification")
	@ApiOperation(value = "Afficher tous les demandes de modification des informations personnelles",
	response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des demandes de modification affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucune demande de modification touvé"), })
	public ResponseEntity<List<DemModificationDTO>> getAllDemModification() {
		
		List<DemModification> DemModifications = DemModificationService.getAllDem();
		
		if (!CollectionUtils.isEmpty(DemModifications)) {
			DemModifications.removeAll(Collections.singleton(null));
			List<DemModificationDTO> DemModificationDTOs = DemModifications.stream().map(DemModification -> {
				return mapDemModificationToDemModificationDTO(DemModification);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<DemModificationDTO>>(DemModificationDTOs, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<DemModificationDTO>>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/deleteDemande/{id}")
	@ApiOperation(value = "Supprimer une demande si elle existe", response = String.class)
	@ApiResponse(code = 204, message = "La demande de modification a été supprimé avec succées")
	public ResponseEntity<String> deleteDemModification(@PathVariable Integer id) {
		DemModificationService.deleteDem(id);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/updateDem")
	public ResponseEntity<DemModificationDTO> updateDem(@RequestBody DemModificationDTO demDTORequest) {
		DemModification demRequest = mapDemModificationDTOToDemModification(demDTORequest);
		if (!DemModificationService.checkIfIdExists(demDTORequest.getId())) {
			return new ResponseEntity<DemModificationDTO>(HttpStatus.NOT_FOUND);
		}
		DemModification demModif = DemModificationService.updateDem(demRequest);
		if (demModif != null) {
			DemModificationDTO demandeDTO = mapDemModificationToDemModificationDTO(demModif);
			return new ResponseEntity<DemModificationDTO>(demandeDTO, HttpStatus.OK);
		}
		return new ResponseEntity<DemModificationDTO>(HttpStatus.NOT_MODIFIED);
	}

	@PostMapping("/myDemandes/{email}")
	@ApiOperation(value = "Afficher tous les demandes d'avance sur salaire", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des demandes affiché avec succées"),
				@ApiResponse(code = 204, message = "Aucune demande pour avance sur salaire touvé"), })
	public ResponseEntity<List<DemModificationDTO>> getAllDemandeByUser(@PathVariable String email) {
		List<DemModification> demModif = DemModificationService.findByuserEmail(email);
		if (!CollectionUtils.isEmpty(demModif)) {
			demModif.removeAll(Collections.singleton(null));
			List<DemModificationDTO> demDtos = demModif.stream().map(dem -> {
					return mapDemModificationToDemModificationDTO(dem);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<DemModificationDTO>>(demDtos, HttpStatus.OK);
		}
		return new ResponseEntity<List<DemModificationDTO>>(HttpStatus.NO_CONTENT);
	}
	
	private DemModificationDTO mapDemModificationToDemModificationDTO(DemModification DemModification) {
		ModelMapper mapper = new ModelMapper();
		DemModificationDTO DemModificationDTO = mapper.map(DemModification, DemModificationDTO.class);
		return DemModificationDTO;
	}

	private DemModification mapDemModificationDTOToDemModification(DemModificationDTO DemModificationDTO) {
		ModelMapper mapper = new ModelMapper();
		DemModification DemModification = mapper.map(DemModificationDTO, DemModification.class);
		return DemModification;
	}

}
