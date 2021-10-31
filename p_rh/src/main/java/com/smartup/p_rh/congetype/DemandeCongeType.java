package com.smartup.p_rh.congetype;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee_conges_dem_type")
public class DemandeCongeType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_conges_dem_type_id")
	private Integer id;

	@Column(name = "employee_conges_dem_dem_type")
	private String type;

	public DemandeCongeType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DemandeCongeType(Integer id, String type) {
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

}
