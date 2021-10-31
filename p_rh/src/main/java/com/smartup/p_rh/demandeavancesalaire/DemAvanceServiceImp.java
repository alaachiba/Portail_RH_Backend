package com.smartup.p_rh.demandeavancesalaire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("Demande Avance salaire service")
@Transactional
public class DemAvanceServiceImp implements IDemAvanceService {

	@Autowired
	DemAvanceRepository demAvanceDao;

	@Override
	public DemAvance saveDem(DemAvance demAvance) {
		return demAvanceDao.save(demAvance);
	}

	@Override
	public boolean checkIfIdExists(Integer id) {
		return demAvanceDao.existsById(id);
	}

	@Override
	public void deleteDemAvance(Integer id) {
		demAvanceDao.deleteById(id);

	}

	@Override
	public List<DemAvance> getAllDemAvance() {
		return demAvanceDao.findAll();
	}

	@Override
	public DemAvance findDemByIdU(Integer user) {
		return demAvanceDao.findDemById(user);
	}

	@Override
	public DemAvance updateDem(DemAvance demandeAvance) {
		return demAvanceDao.save(demandeAvance);
	}

	@Override
	public List<DemAvance> findByuser(Integer user) {
		return demAvanceDao.findByuser(user);
	}

	@Override
	public List<DemAvance> findByuserEmail(String email) {
		return demAvanceDao.findDemByEmail(email);
	}
}
