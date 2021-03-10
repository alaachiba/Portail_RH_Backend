package com.smartup.p_rh.demandeconge;

import java.util.Date;

import com.smartup.p_rh.users.UserDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Modele demande de congé")
public class DemandeCongeDTO implements Comparable<DemandeCongeDTO> {

	@ApiModelProperty(notes = "Id de la demande de conge")
	private Integer id;

	@ApiModelProperty(notes = "La date de début de congé")
	private Date dateDeb;

	@ApiModelProperty(notes = "La date de fin de congé")
	private Date dateFin;

	@ApiModelProperty(notes = "Le nombre des jours de congé")
	private Integer nbrJrs;

	@ApiModelProperty(notes = "La motif de congé")
	private String motif;

	@ApiModelProperty(notes = "Id du remplaçant")
	private Integer idRemplacant;

	@ApiModelProperty(notes = "Les taches du delegué au remplaçant ")
	private String tacheDele;

	@ApiModelProperty(notes = "Le status de l'employée")
	private String status;

	@ApiModelProperty(notes = "La date de la demande de congé")
	private Date dateDem;

	@ApiModelProperty(notes = "Le demandeur de congé")
	private UserDTO user;


	
	

	public DemandeCongeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public DemandeCongeDTO(Integer id, Date dateDeb, Date dateFin, Integer nbrJrs, String motif, Integer idRemplacant,
			String tacheDele, String status, Date dateDem ) {
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
		this.dateDem = dateDem;
	}



	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	

	@Override
	public int compareTo(DemandeCongeDTO o) {
		return 	motif.compareToIgnoreCase(o.getMotif());

	}

	

}
