package com.smartup.p_rh.demandefraispro;

import java.util.List;


public interface IDemFraisService {
	
public DemFrais saveDem(DemFrais demFrais);
	
	public List<DemFrais> getAllDem();
		
	public void deleteDem(Integer id);
	
	//public List<DemFrais> getAllDemForThisUser(Integer id);

}
