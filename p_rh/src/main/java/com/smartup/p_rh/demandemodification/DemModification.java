package com.smartup.p_rh.demandemodification;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smartup.p_rh.demandemodification.type.DemModificationType;
import com.smartup.p_rh.users.User;

@Entity
@Table(name = "employee_infos_rh_dem")
public class DemModification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_infos_rh_dem_id")
	private Integer id;
	
	@Column(name = "employee_infos_rh_dem_descripti")
	private String description;
	
	@Column(name = "employee_infos_rh_dem_statu")
	private String statusDem;
	
	@Column(name = "employee_infos_rh_dem_date")
	private Date dateDem;
	
	@ManyToOne
	@JoinColumn(name= "users_login")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="demModification")
	private DemModificationType demModificationType;
	

	public DemModification() {
		super();
	}
	
	public DemModification(String description, String statusDem, Date dateDem, Integer id) {
		super();
		this.description = description;
		this.statusDem = statusDem;
		this.dateDem = dateDem;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getIdUser() {
		return user;
	}
	public void setIdUser(User user) {
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
	
	public Date getDateDem() {
		return dateDem;
	}

	public void setStatusDem(String statusDem) {
		this.statusDem = statusDem;
	}

	public void setDateDem(Date dateDem) {
		this.dateDem = dateDem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public DemModificationType getDemModificationType() {
		return demModificationType;
	}

	public void setDemModificationType(DemModificationType demModificationType) {
		this.demModificationType = demModificationType;
	}

	@Override
	public String toString() {
		return "DemModification [id=" + id + ", description=" + description + ", statusDem=" + statusDem + ", dateDem="
				+ dateDem + ", user=" + user + ", demModificationType=" + demModificationType + "]";
	}
	
	
	

}
