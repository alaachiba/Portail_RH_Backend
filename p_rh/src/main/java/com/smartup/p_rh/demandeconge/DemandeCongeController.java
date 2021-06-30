package com.smartup.p_rh.demandeconge;

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
@RequestMapping("/rest/api/congeDem")
@Api(value = "Rest Controller: Demande de congé")
public class DemandeCongeController {
	
	@Autowired
	private DemandeCongeServiceImp CongeService;
	
	@PostMapping("/addDemandeConge")
	@ApiOperation(value = "Ajouter une demande de congé", response = DemandeCongeDTO.class)
	@ApiResponse(code = 201, message = "La demande de congé à été envoyé avec succées")
	public ResponseEntity<DemandeCongeDTO> createNewDemConge(@RequestBody DemandeCongeDTO demDTORequest ){
		DemandeConge demRequest = mapDemandeCongeDTOToDemandeConge(demDTORequest);	
		DemandeConge demandeConge = CongeService.saveDemConge(demRequest);	
		DemandeCongeDTO demandeCongeDTO = mapDemandeCongeToDemandeCongeDTO(demandeConge);
		return new ResponseEntity<DemandeCongeDTO>(demandeCongeDTO ,  HttpStatus.CREATED);
	}
	
	@GetMapping("/allDemandeConge")
	@ApiOperation(value = "Afficher tous les demandes de congé", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des demandes de congé affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucune demande de congé touvé"), })
	public ResponseEntity<List<DemandeCongeDTO>> getAllDem(){		
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
	public ResponseEntity<DemandeCongeDTO> deleteDemConge(@PathVariable Integer id){
		CongeService.deleteDemConge(id);
		return new ResponseEntity<DemandeCongeDTO>(HttpStatus.NO_CONTENT);
	}
	
	private DemandeCongeDTO mapDemandeCongeToDemandeCongeDTO(DemandeConge demandeConge) {
		ModelMapper mapper = new ModelMapper();
		DemandeCongeDTO demandeCongeDTO = mapper.map(demandeConge , DemandeCongeDTO.class);
			return demandeCongeDTO;
		}

	private DemandeConge mapDemandeCongeDTOToDemandeConge(DemandeCongeDTO demDTORequest) {
		ModelMapper mapper = new ModelMapper();
		DemandeConge demandeConge = mapper.map( demDTORequest, DemandeConge.class);
		return demandeConge;
	}

}
