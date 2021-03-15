package com.smartup.p_rh.demandefraispro;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.smartup.p_rh.users.User;

@Entity
@Table(name = "employee_frais_dem")
public class DemFrais {
	
	 @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="employee_frais_dem_id")
	private Integer id;
	
	@Column(name = "employee_frais_dem_montant")
	private float montant;
	
	@Column(name = "employee_frais_dem_date_mission")
	private Date dateMission;
	
	@Column(name = "employee_frais_dem_date")
	private Date dateDem;
	
	@ManyToOne
	@JoinColumn(name = "users_login")
	private User user;
	
	public DemFrais() {
		super();
	}

	public DemFrais(Integer id, float montant, Date dateMission, Date dateDem) {
		super();
		this.id = id;
		this.montant = montant;
		this.dateMission = dateMission;
		this.dateDem = dateDem;
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

	public Date getDateMission() {
		return dateMission;
	}

	public void setDateMission(Date dateMission) {
		this.dateMission = dateMission;
	}

	public Date getDateDem() {
		return dateDem;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public void setDateDem(Date dateDem) {
		dateDem = new Date();
		this.dateDem = dateDem;
	}

	@Override
	public String toString() {
		return "DemFrais [id=" + id + ", montant=" + montant + ", dateMission=" + dateMission + ", dateDem=" + dateDem
				+ ", user=" + user + "]";
	}
	
	
}
