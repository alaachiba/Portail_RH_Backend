package com.smartup.p_rh.message.request;

import java.util.Set;

import javax.persistence.Column;

public class SignUpForm {
	//@NotBlank
    //@Size(min=3, max = 50)
	@Column(name= "users_nom")
	private String nom;
	
    //@NotBlank
    //@Size(min=3, max = 50)
	@Column(name= "users_prenom")
	private String prenom;
	
    //@NotBlank
	@Column(name= "employee_matricul")
	private Integer matricule;
	
    //@NaturalId
    //@NotBlank
    //@Size(max = 50)
    //@Email
	@Column(name= "users_email")
	private String email;
	
	@Column(name= "username")
	private String username;
	
    //@NotBlank
	@Column(name= "users_status")
	private String status;
	
	//@NotBlank
    //@Size(min=6, max = 100)
	@Column(name= "users_pwd")
	private String pwd;
    
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