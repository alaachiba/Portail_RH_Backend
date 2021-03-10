package com.smartup.p_rh.users;

import java.util.HashSet;
import java.util.Set;

import com.smartup.p_rh.role.Role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Modele de l'utilisateur")
public class UserDTO {

	@ApiModelProperty(notes = "Id de l'utilisateur")
	private Integer idUser;

	@ApiModelProperty(notes = "La mot de passe crypté de l'utlisateur")
	private String pwd;

	@ApiModelProperty(notes = "Le nom de l'utilisateur")
	private String nom;

	@ApiModelProperty(notes = "Le prenom de l'utilisateur")
	private String prenom;

	@ApiModelProperty(notes = "Le matricule de l'utlisateur")
	private Integer matricule;

	@ApiModelProperty(notes = "L'adresse mail de l'utilisateur")
	private String email;
	
	@ApiModelProperty(notes = "Le login de l'utilisateur")
	private String username;

	@ApiModelProperty(notes = "Le status de l'utilisateur")
	private String status;
	
	@ApiModelProperty(notes = "Le role de l'utilisateur")
	  private Set<Role> roles = new HashSet<>();
	
	
	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

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


	

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserDTO [idUser=" + idUser + ", pwd=" + pwd + ", nom=" + nom + ", prenom=" + prenom + ", matricule="
				+ matricule + ", email=" + email + ", username=" + username + ", status=" + status + ", roles=" + roles
				+ "]";
	}



	
	
	

}
