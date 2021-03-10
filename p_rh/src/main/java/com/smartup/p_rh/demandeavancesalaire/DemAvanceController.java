package com.smartup.p_rh.demandeavancesalaire;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest/api")
@Api(value = "Rest Controller: Demande avance sur salaire")
public class DemAvanceController {

	@Autowired
	private DemAvanceServiceImp demAvanceService;
	
	//date
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
