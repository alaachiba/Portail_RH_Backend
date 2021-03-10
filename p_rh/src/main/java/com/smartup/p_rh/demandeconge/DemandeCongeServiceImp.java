package com.smartup.p_rh.demandeconge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("DemandeCongeService")
@Transactional
public class DemandeCongeServiceImp implements IDemandeCongeService {
	
	@Autowired
	private DemandeCongeRepository demandeCongeRepository;

	@Override
	public DemandeConge saveDemConge(DemandeConge demandeConge) {
		return demandeCongeRepository.save(demandeConge);
	}

	@Override
	public List<DemandeConge> getAllDemConge() {
		return demandeCongeRepository.findAll();
	}

	@Override
	public void deleteDemConge(Integer id) {
		demandeCongeRepository.deleteById(id);
	}
		

}
