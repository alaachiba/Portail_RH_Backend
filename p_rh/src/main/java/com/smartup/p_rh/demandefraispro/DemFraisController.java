package com.smartup.p_rh.demandefraispro;

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
	
	//date
	@PostMapping("/addDemFrais")
	@ApiOperation(value = "Ajouter une demande des frais professionels", response = DemFraisDTO.class)
	@ApiResponse(code = 201, message = "La demande de frais a été envoyé avec succées")
	public ResponseEntity<DemFraisDTO> createNewDemFrais(@RequestBody DemFraisDTO demDTORequest) {
		DemFrais demRequest = mapDemFraisDTOToDemFrais(demDTORequest);
		System.out.println(demRequest);
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
