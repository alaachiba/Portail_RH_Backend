package com.smartup.p_rh.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartup.p_rh.users.User;

public class UserPrinciple implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Integer idUser;
	 	 
	    private String nom;
	 
	    private String prenom;
	    
	    private Integer matricule;
	    
		private String email;
		
		private String username;
		
		private String status;
	 
	    @JsonIgnore
	    private String pwd;
	    
	    private Collection<? extends GrantedAuthority> authorities;



	
	
	
	 public UserPrinciple(Integer idUser, String nom, String prenom, Integer matricule, String email,
				String username, String status, String pwd, Collection<? extends GrantedAuthority> authorities) {
			super();
			this.idUser = idUser;
			this.nom = nom;
			this.prenom = prenom;
			this.matricule = matricule;
			this.email = email;
			this.username = username;
			this.status = status;
			this.pwd = pwd;
			this.authorities = authorities;
		}




	public static UserPrinciple build(User user) {
	        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
	                new SimpleGrantedAuthority(role.getLibelle().name())
	        ).collect(Collectors.toList());
	 
	        return new UserPrinciple(
	        		user.getIdUser(),
	        		user.getNom(),
	        		user.getPrenom(),
	        		user.getMatricule(), 
	        		user.getEmail(), 
	        		user.getUsername(),
	        		user.getStatus(),
	        		user.getPwd(),
	        		authorities
	        		);
	                
	    }




	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}





	public Integer getIdUser() {
		return idUser;
	}




	public String getNom() {
		return nom;
	}




	public String getPrenom() {
		return prenom;
	}




	public Integer getMatricule() {
		return matricule;
	}




	public String getEmail() {
		return email;
	}




	public String getStatus() {
		return status;
	}




	
	
	public String getPwd() {
		return pwd;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	   @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        
	        UserPrinciple user = (UserPrinciple) o;
	        return Objects.equals(idUser, user.idUser);
	    }




	@Override
	public String getPassword() {
		return pwd;
	}




	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	
}