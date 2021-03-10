package com.smartup.p_rh.demandemodification.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smartup.p_rh.demandemodification.DemModification;

@Entity
@Table(name = "employee_infos_rh_dem_type")
public class DemModificationType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_infos_rh_dem_type_id")
	private Integer id;
	
	
	@Column(name = "employee_infos_rh_dem_type")
	private String type;
	
	
	
	@ManyToOne
	@JoinColumn(name= "employee_infos_rh_dem_id")
	private DemModification demModification;
	
	public DemModificationType() {
		super();
	}

	public DemModificationType(Integer id, String type) {
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

	public DemModification getDemModification() {
		return demModification;
	}

	public void setDemModification(DemModification demModification) {
		this.demModification = demModification;
	}

	@Override
	public String toString() {
		return "DemModificationType [id=" + id + ", type=" + type + "]";
	}
	
	
	
	

}
