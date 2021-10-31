package com.smartup.p_rh.demandemodification.type;

import java.util.List;

public interface IdemModificationTypeService {

	public DemModificationType saveDemModif(DemModificationType DemModificationType);

	public List<DemModificationType> getAlldemModif();

	public void deleteDemModif(Integer id);

	public DemModificationType updateModifType(DemModificationType DemModificationType);

}
