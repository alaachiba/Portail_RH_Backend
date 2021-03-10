package com.smartup.p_rh.congetype;


import java.util.Collection;

import com.smartup.p_rh.demandeconge.DemandeCongeDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel (value = "Modele de type de congé")
public class DemandeCongeTypeDTO implements Comparable<DemandeCongeTypeDTO> {
	
 
	@ApiModelProperty(notes = "Id de type de congé")
     private Integer id;
	
	@ApiModelProperty(notes = "Le type de congé")
	 private String type;
	
	@ApiModelProperty(value = "Le type de la demande conge")
	private Collection<DemandeCongeDTO> demandeConge;
	
	
	
	public DemandeCongeTypeDTO(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	

	public DemandeCongeTypeDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	
	public Collection<DemandeCongeDTO> getDemandeConge() {
		return demandeConge;
	}

	public void setDemandeConge(Collection<DemandeCongeDTO> demandeConge) {
		this.demandeConge = demandeConge;
	}

	@Override
	public int compareTo(DemandeCongeTypeDTO o) {
		// TODO Auto-generated method stub
		return type.compareToIgnoreCase(o.getType());
	}

}
