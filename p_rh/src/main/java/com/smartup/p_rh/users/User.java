package com.smartup.p_rh.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.smartup.p_rh.role.Role;

@Entity
@Table(name = "users")
public class User{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "users_login")
	private Integer idUser;
	
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
	
    //@NotBlank
	@Column(name= "users_status")
	private String status;
	
	//@NotBlank
    //@Size(min=6, max = 100)
	@Column(name= "users_pwd")
	private String pwd;
	
	@Column(name= "username")
	private String username;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", 
    	joinColumns = @JoinColumn(name = "users_login"), 
    	inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();
	
	public User() {
		super();
	}

	

	public User(String nom, String prenom, Integer matricule, String email, String status, String pwd, String username) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.matricule = matricule;
		this.email = email;
		this.status = status;
		this.pwd = pwd;
		this.username = username;
	}



	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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
		return "User [idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", matricule=" + matricule
				+ ", email=" + email + ", status=" + status + ", pwd=" + pwd + ", username=" + username + ", roles="
				+ roles + "]";
	}

	
}