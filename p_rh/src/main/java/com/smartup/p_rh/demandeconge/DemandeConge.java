package com.smartup.p_rh.demandeconge;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smartup.p_rh.congetype.DemandeCongeType;
import com.smartup.p_rh.users.User;

@Entity
@Table(name = "employee_conges_dem")
public class DemandeConge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_conge_dem_id")
	private Integer id;
	
	@Column(name= "employee_conges_dem_date_deb")
	private Date dateDeb;
	
	@Column(name= "employee_conges_dem_date_fin")
	private Date dateFin;
	
	@Column(name= "employee_conges_dem_nbr_jrs")
	private Integer nbrJrs;
	
	@Column(name= "employee_conges_dem_motif")
	private String motif;
	
	@Column(name= "employee_id_remplacant")
	private Integer idRemplacant;
	
	@Column(name= "employee_conges_dem_taches_dele")
	private String tacheDele;
	
	@Column(name= "employee_conges_dem_statut")
	private String status;
	
	@Column(name= "employee_conges_dem_date")
	private Date dateDem;
	
	@ManyToOne
	@JoinColumn(name= "users_login")
	private User user;
	
	@ManyToOne
	@JoinColumn(name= "employee_conges_dem_type_id")
	private DemandeCongeType demandeCongeType;
	
	

	public DemandeConge() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DemandeConge(Integer id, Date dateDeb, Date dateFin, Integer nbrJrs, String motif, Integer idRemplacant,
			String tacheDele, String status, Date dateDem, DemandeCongeType demandeCongeType) {
		super();
		this.id = id;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.nbrJrs = nbrJrs;
		this.motif = motif;
		this.idRemplacant = idRemplacant;
		this.tacheDele = tacheDele;
		this.status = status;
		this.dateDem = dateDem;
		this.demandeCongeType = demandeCongeType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getNbrJrs() {
		return nbrJrs;
	}

	public void setNbrJrs(Integer nbrJrs) {
		this.nbrJrs = nbrJrs;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Integer getIdRemplacant() {
		return idRemplacant;
	}

	public void setIdRemplacant(Integer idRemplacant) {
		this.idRemplacant = idRemplacant;
	}

	public String getTacheDele() {
		return tacheDele;
	}

	public void setTacheDele(String tacheDele) {
		this.tacheDele = tacheDele;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateDem() {
		return dateDem;
	}

	

	public void setDateDem(Date dateDem) {
		dateDem = new Date();
		this.dateDem = dateDem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DemandeCongeType getDemandeCongeType() {
		return demandeCongeType;
	}

	public void setDemandeCongeType(DemandeCongeType demandeCongeType) {
		this.demandeCongeType = demandeCongeType;
	}

}
