package com.smartup.p_rh.demandemodification.type;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/modifInfoType")
public class DemModificationTypeController {

	@Autowired
	private DemModificationTypeServiceImp DemModificationTypeService;
	
	@GetMapping("/ListDemModificationType")
	public ResponseEntity<List<DemModificationTypeDTO>> getAllDemModification() {
		List<DemModificationType> DemModifications = DemModificationTypeService.getAlldemModif();
		
		if (!CollectionUtils.isEmpty(DemModifications)) {
			DemModifications.removeAll(Collections.singleton(null));
			List<DemModificationTypeDTO> DemModificationDTOs = DemModifications.stream().map(DemModification -> {
				return mapDemModificationToDemModificationDTO(DemModification);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<DemModificationTypeDTO>>(DemModificationDTOs, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<DemModificationTypeDTO>>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/addDemModificationType")
	public ResponseEntity<DemModificationTypeDTO> createNewDemModification(
			@RequestBody DemModificationTypeDTO DemModificationDTORequest) {
		DemModificationType DemModificationRequest = mapDemModificationDTOToDemModification(DemModificationDTORequest);
		DemModificationType DemModification = DemModificationTypeService.saveDemModif(DemModificationRequest);
		if (DemModification != null && DemModification.getId() != null) {
			DemModificationTypeDTO DemModificationDTO = mapDemModificationToDemModificationDTO(DemModification);
			return new ResponseEntity<DemModificationTypeDTO>(DemModificationDTO, HttpStatus.CREATED);
		}
		return new ResponseEntity<DemModificationTypeDTO>(HttpStatus.NOT_MODIFIED);
	}
	
	private DemModificationTypeDTO mapDemModificationToDemModificationDTO(DemModificationType DemModification) {
		ModelMapper mapper = new ModelMapper();
		DemModificationTypeDTO DemModificationDTO = mapper.map(DemModification, DemModificationTypeDTO.class);
		return DemModificationDTO;
	}

	private DemModificationType mapDemModificationDTOToDemModification(DemModificationTypeDTO DemModificationDTO) {
		ModelMapper mapper = new ModelMapper();
		DemModificationType DemModification = mapper.map(DemModificationDTO, DemModificationType.class);
		return DemModification;
	}
}
