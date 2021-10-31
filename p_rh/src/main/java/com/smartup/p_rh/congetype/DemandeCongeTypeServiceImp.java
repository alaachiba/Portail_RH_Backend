package com.smartup.p_rh.congetype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("typeCongeService")
@Transactional
public class DemandeCongeTypeServiceImp implements IDemandeCongeTypeService {

	@Autowired
	private DemandeCongeTypeRepository demandeCongeTypeRepository;

	@Override
	public DemandeCongeType saveType(DemandeCongeType demandeCongeType) {
		return demandeCongeTypeRepository.save(demandeCongeType);
	}

	@Override
	public DemandeCongeType updateType(DemandeCongeType demandeCongeType) {
		return demandeCongeTypeRepository.save(demandeCongeType);
	}

	@Override
	public boolean checkIfIdExists(Integer id) {
		return demandeCongeTypeRepository.existsById(id);
	}

	@Override
	public void deleteType(Integer id) {
		demandeCongeTypeRepository.deleteById(id);

	}

	@Override
	public List<DemandeCongeType> getAllTypeConge() {
		return demandeCongeTypeRepository.findAll();

	}

}
