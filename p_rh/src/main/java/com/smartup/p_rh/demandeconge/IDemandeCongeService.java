package com.smartup.p_rh.demandeconge;

import java.util.List;


public interface IDemandeCongeService {
	
	public DemandeConge saveDemConge(DemandeConge demandeConge);
	
	public List<DemandeConge> getAllDemConge();
		
	public void deleteDemConge(Integer id);

}
