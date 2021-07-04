package com.smartup.p_rh.demandefraispro;

import java.util.Date;

import com.smartup.p_rh.users.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "Modele de demande des frais profetionnels")
public class DemFraisDTO {
	
	@ApiModelProperty(notes = "Id de demande frais")
    private Integer id;
	
	@ApiModelProperty(notes = "Le montant de frais")
	private float montant;
	
	@ApiModelProperty(notes = "La date le la mission")
	private Date dateMission;
	
	@ApiModelProperty(notes = "La date de la demande de frais profetionnels")
	private Date dateDem;
	
	@ApiModelProperty(notes = "Le motif en cas de refus")
	private String motif;
	
	@ApiModelProperty(notes = "Le demandeur des frais profetionnels")
	private User user;
	
	

	public DemFraisDTO() {
		super();
	}
	

	public DemFraisDTO(Integer id, float montant, Date dateMission, Date dateDem, String motif) {
		super();
		this.id = id;
		this.montant = montant;
		this.dateMission = dateMission;
		this.dateDem = dateDem;
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
		this.dateDem = dateDem;
	}


	public String getMotif() {
		return motif;
	}


	public void setMotif(String motif) {
		this.motif = motif;
	}
	
	
	
	

}
