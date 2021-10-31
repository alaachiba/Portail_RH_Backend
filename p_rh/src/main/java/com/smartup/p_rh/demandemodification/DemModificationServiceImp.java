package com.smartup.p_rh.demandemodification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("demande modification service")
@Transactional
public class DemModificationServiceImp implements IDemModificationService {

	@Autowired
	DemModificationRepository demModificationDao;

	@Override
	public DemModification saveDem(DemModification demModification) {
		return demModificationDao.save(demModification);
	}

	@Override
	public void deleteDem(Integer id) {
		demModificationDao.deleteById(id);

	}

	@Override
	public List<DemModification> getAllDem() {
		return demModificationDao.findAll();
	}

	public boolean checkIfIdExists(Integer id) {
		return demModificationDao.existsById(id);
	}

	public DemModification updateDem(DemModification demRequest) {
		return demModificationDao.save(demRequest);
	}

	@Override
	public List<DemModification> findByuserEmail(String email) {
		return demModificationDao.findDemByEmail(email);
	}

}
