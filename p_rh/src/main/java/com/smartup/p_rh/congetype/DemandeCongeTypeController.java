package com.smartup.p_rh.congetype;

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
@RequestMapping("/rest/api/congeType")
@CrossOrigin(origins = "*")
@Api(value = "Rest Controller: Types congé")
public class DemandeCongeTypeController {

	@Autowired
	private DemandeCongeTypeServiceImp typeService;

	@PostMapping("/addType")
	@ApiOperation(value = "Ajouter un nouveau type de conge", response = DemandeCongeTypeDTO.class)
	@ApiResponse(code = 201, message = "Le type de congé a été créé avec succées")
	public ResponseEntity<DemandeCongeTypeDTO> createNewType(@RequestBody DemandeCongeTypeDTO typeDTORequest) {
		DemandeCongeType typeRequest = mapDemandeTypeCongeDTOToDemandeTypeConge(typeDTORequest);
		DemandeCongeType demandeCongeType = typeService.saveType(typeRequest);
		DemandeCongeTypeDTO demandeCongeTypeDTO = mapDemandeCongeTypeToDemandeCongeTypeDTO(demandeCongeType);
		return new ResponseEntity<DemandeCongeTypeDTO>(demandeCongeTypeDTO, HttpStatus.CREATED);
	}

	@GetMapping("/allType")
	@ApiOperation(value = "Afficher tous les types de congé", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "La liste des types de congé affiché avec succées"),
			@ApiResponse(code = 204, message = "Aucun type de congé touvé"), })
	public ResponseEntity<List<DemandeCongeTypeDTO>> getAllTypeConge() {
		List<DemandeCongeType> demandeCongeTypes = typeService.getAllTypeConge();
		if (!CollectionUtils.isEmpty(demandeCongeTypes)) {
			demandeCongeTypes.removeAll(Collections.singleton(null));
			List<DemandeCongeTypeDTO> demandeCongeTypeDTOs = demandeCongeTypes.stream().map(demandeCongeType -> {
				return mapDemandeCongeTypeToDemandeCongeTypeDTO(demandeCongeType);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<DemandeCongeTypeDTO>>(demandeCongeTypeDTOs, HttpStatus.OK);
		}
		return new ResponseEntity<List<DemandeCongeTypeDTO>>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteType/{id}")
	@ApiOperation(value = "Supprimer un type de congé s'il existe", response = String.class)
	@ApiResponse(code = 204, message = "Le type de congé a été supprimé avec succées")
	public ResponseEntity<DemandeCongeTypeDTO> deleteType(@PathVariable Integer id) {
		typeService.deleteType(id);
		return new ResponseEntity<DemandeCongeTypeDTO>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/updateTypeConge")
	@ApiOperation(value = "Modifier un type de congé existant", response = DemandeCongeTypeDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Le type de congé n'existe pas"),
			@ApiResponse(code = 200, message = "Le type de congé a été modifié"),
			@ApiResponse(code = 304, message = "Erreur lors de la modification de type de congé") })
	public ResponseEntity<DemandeCongeTypeDTO> updateType(@RequestBody DemandeCongeTypeDTO typeDTORequest) {
		DemandeCongeType typeRequest = mapDemandeTypeCongeDTOToDemandeTypeConge(typeDTORequest);
		if (!typeService.checkIfIdExists(typeDTORequest.getId())) {
			return new ResponseEntity<DemandeCongeTypeDTO>(HttpStatus.NOT_FOUND);
		}
		DemandeCongeType demandeCongeType = typeService.updateType(typeRequest);
		if (demandeCongeType != null) {
			DemandeCongeTypeDTO demandeCongeTypeDTO = mapDemandeCongeTypeToDemandeCongeTypeDTO(demandeCongeType);
			return new ResponseEntity<DemandeCongeTypeDTO>(demandeCongeTypeDTO, HttpStatus.OK);
		}
		return new ResponseEntity<DemandeCongeTypeDTO>(HttpStatus.NOT_MODIFIED);
	}

	private DemandeCongeTypeDTO mapDemandeCongeTypeToDemandeCongeTypeDTO(DemandeCongeType demandeCongeType) {
		ModelMapper mapper = new ModelMapper();
		DemandeCongeTypeDTO demandeCongeTypeDTO = mapper.map(demandeCongeType, DemandeCongeTypeDTO.class);
		return demandeCongeTypeDTO;
	}

	private DemandeCongeType mapDemandeTypeCongeDTOToDemandeTypeConge(DemandeCongeTypeDTO demandeCongeTypeDTO) {
		ModelMapper mapper = new ModelMapper();
		DemandeCongeType demandeCongeType = mapper.map(demandeCongeTypeDTO, DemandeCongeType.class);
		return demandeCongeType;
	}
}
