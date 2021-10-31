package com.smartup.p_rh.message.request;

import java.util.Set;

import javax.persistence.Column;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Modele de formulaire de signup")
public class SignUpForm {

	@ApiModelProperty(notes = "Le nom de l'utilisateur")
	@Column(name = "users_nom")
	private String nom;

	@ApiModelProperty(notes = "Le prenom de l'utilisateur")
	@Column(name = "users_prenom")
	private String prenom;

	@ApiModelProperty(notes = "Le matricule de l'utilisateur")
	@Column(name = "employee_matricul")
	private Integer matricule;

	@ApiModelProperty(notes = "L'adresse mail de l'utilisateur")
	@Column(name = "users_email")
	private String email;

	@ApiModelProperty(notes = "Le nom d'utilisateur")
	@Column(name = "username")
	private String username;

	@ApiModelProperty(notes = "Le status de l'utilisateur")
	@Column(name = "users_status")
	private String status;

	@ApiModelProperty(notes = "La mot de passe de l'utilisateur")
	@Column(name = "users_pwd")
	private String pwd;

	@ApiModelProperty(notes = "Le rôle de l'utilisateur")
	private Set<String> role;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getMatricule() {
		return matricule;
	}

	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}