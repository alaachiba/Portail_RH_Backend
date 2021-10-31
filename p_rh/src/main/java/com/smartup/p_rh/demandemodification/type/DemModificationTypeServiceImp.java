package com.smartup.p_rh.demandemodification.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("DemandeModificationType")
@Transactional
public class DemModificationTypeServiceImp implements IdemModificationTypeService {

	@Autowired
	private DemModificationTypeRepository demModifTypeDao;

	@Override
	public DemModificationType saveDemModif(DemModificationType demModificationType) {
		return demModifTypeDao.save(demModificationType);
	}

	@Override
	public List<DemModificationType> getAlldemModif() {
		return demModifTypeDao.findAll();
	}

	@Override
	public void deleteDemModif(Integer id) {
		demModifTypeDao.deleteById(id);
	}

	@Override
	public DemModificationType updateModifType(DemModificationType DemModificationType) {
		return demModifTypeDao.save(DemModificationType);
	}

}
