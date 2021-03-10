package com.smartup.p_rh.congetype;

import java.util.List;

public interface IDemandeCongeTypeService {
	
	public DemandeCongeType saveType(DemandeCongeType demandeCongeType);
	
	public DemandeCongeType updateType(DemandeCongeType demandeCongeType);
	
	public boolean checkIfIdExists(Integer id);
	
	public void deleteType(Integer id);
	
	public List<DemandeCongeType> getAllTypeConge();



}
 