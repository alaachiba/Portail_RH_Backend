package com.smartup.p_rh.demandeavancesalaire;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smartup.p_rh.users.User;

@Entity
@Table(name = "employee_avance_salaire_dem")
public class DemAvance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_av_salaire_dem_id")
	private Integer id;

	@Column(name = "employee_av_salaire_dem_montant")
	private float montant;

	@Column(name = "employee_av_salaire_dem_date")
	private Date demAvance;

	@ManyToOne
	@JoinColumn(name = "users_login")
	private User user;

	public DemAvance() {
		super();
	}

	public DemAvance(Integer id, float montant, Date demAvance) {
		super();
		this.id = id;
		this.montant = montant;
		this.demAvance = demAvance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public Date getDemAvance() {
		return demAvance;
	}

	

	public void setDemAvance(Date demAvance) {
		this.demAvance = demAvance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
