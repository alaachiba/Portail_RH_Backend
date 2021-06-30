package com.smartup.p_rh.demandemodification;

import java.util.Date;

import com.smartup.p_rh.demandemodification.type.DemModificationTypeDTO;
import com.smartup.p_rh.users.UserDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Modele demande de modification des informations personnelles")
public class DemModificationDTO implements Comparable<DemModificationDTO>{
	
	@ApiModelProperty(notes = "Id de la demande modification")
	private Integer id;
	
	@ApiModelProperty(notes = "La description de la demande")
	private String description;
	
	@ApiModelProperty(notes = "Le status de la demande")
	private String statusDem;
	
	@ApiModelProperty(notes = "La date de la demande")
	private Date dateDem;
	
	@ApiModelProperty(notes = "Le demandeur de modification des informations personnelles")
	private UserDTO user;
	
	@ApiModelProperty(notes = "Le type de demande")
	private DemModificationTypeDTO demModificationType;

	
	public DemModificationDTO() {
		super();
	}

	public DemModificationDTO(Integer id, String description, String statusDem, Date dateDem) {
		super();
		this.id = id;
		this.description = description;
		this.statusDem = statusDem;
		this.dateDem = dateDem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatusDem() {
		return statusDem;
	}

	public void setStatusDem(String statusDem) {
		this.statusDem = "En attente";
	}

	public Date getDateDem() {
		return dateDem;
	}

	

	public void setDateDem(Date dateDem) {
		dateDem = new Date();
		this.dateDem = dateDem;
	}

	

	public DemModificationTypeDTO getDemModificationType() {
		return demModificationType;
	}

	public void setDemModificationType(DemModificationTypeDTO demModificationType) {
		this.demModificationType = demModificationType;
	}

	@Override
	public int compareTo(DemModificationDTO o) {
		return description.compareToIgnoreCase(o.getDescription());
 
	}

}
