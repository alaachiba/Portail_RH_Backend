package com.smartup.p_rh.demandemodification;

import java.util.List;

public interface IDemModificationService {

	public DemModification saveDem(DemModification demModification);

	public void deleteDem(Integer id);

	public List<DemModification> getAllDem();

	public List<DemModification> findByuserEmail(String email);
}
