package com.smartup.p_rh.demandeavancesalaire;

import java.util.List;

public interface IDemAvanceService {

	public DemAvance saveDem(DemAvance DemAvance);

	public boolean checkIfIdExists(Integer id);

	public void deleteDemAvance(Integer id);

	public List<DemAvance> getAllDemAvance();

}
