package com.smartup.p_rh.demandeavancesalaire;

import java.util.Date;

import com.smartup.p_rh.users.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Modele demande avance salaire")
public class DemAvanceDTO {

	@ApiModelProperty(notes = "id de demande avance salaire")
	private Integer id;

	@ApiModelProperty(notes = "Le montant de l'avance")
	private float montant;

	@ApiModelProperty(notes = "La date de la demande")
	private Date demAvance;
	
	@ApiModelProperty(notes = "Le statut de la demande")
	private String statut;
	
	@ApiModelProperty(notes = "Le motif en cas de refus")
	private String motif;

	@ApiModelProperty(notes = "Le demandeur de l'avance sur salaire")
	private User user;

	public DemAvanceDTO() {
		super();
	}
	

	public DemAvanceDTO(Integer id, float montant, Date demAvance, String statut, String motif) {
		super();
		this.id = id;
		this.montant = montant;
		this.demAvance = demAvance;
		this.statut = statut;
		this.motif = motif;
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


	public String getStatut() {
		return statut;
	}


	public void setStatut(String statut) {
		this.statut = statut;
	}


	public String getMotif() {
		return motif;
	}


	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	

}
