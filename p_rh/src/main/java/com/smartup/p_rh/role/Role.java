package com.smartup.p_rh.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_role")
	private Integer id;
	
    @Enumerated(EnumType.STRING)
	@Column(name = "libelle_role")
	private RoleName libelle;
	
	public Role() {
		super();
	}

	public Role(Integer id, RoleName libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleName getLibelle() {
		return libelle;
	}

	public void setLibelle(RoleName libelle) {
		this.libelle = libelle;
	}

}
