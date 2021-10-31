package com.smartup.p_rh.demandefraispro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("DemandeFraisService")
@Transactional
public class DemFraisServiceImp implements IDemFraisService {

	@Autowired
	private DemFraisRepository demFraisDao;

	@Override
	public DemFrais saveDem(DemFrais demFrais) {
		return demFraisDao.save(demFrais);
	}

	@Override
	public List<DemFrais> getAllDem() {
		return demFraisDao.findAll();
	}

	@Override
	public void deleteDem(Integer id) {
		demFraisDao.deleteById(id);

	}

	public boolean checkIfIdExists(Integer id) {
		return demFraisDao.existsById(id);
	}

	public DemFrais updateDem(DemFrais demRequest) {
		return demFraisDao.save(demRequest);
	}

	@Override
	public List<DemFrais> findByuserEmail(String email) {
		return demFraisDao.findDemByEmail(email);
	}

}
