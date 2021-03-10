package com.smartup.p_rh.demandemodification.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Modele type de demande de modification des informations personnelles")
public class DemModificationTypeDTO implements Comparable<DemModificationTypeDTO> {
	
	@ApiModelProperty(notes = "Id type de champs ce que l'employé veut changé")
	private Integer id;
	
	@ApiModelProperty(notes = "Le libelle de champs ce que l'employé veut changé")
	private String type;
	

	public DemModificationTypeDTO() {
		super();
	}



	public DemModificationTypeDTO(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
		
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int compareTo(DemModificationTypeDTO o) {
		return 	type.compareToIgnoreCase(o.getType());
	}
	



}
