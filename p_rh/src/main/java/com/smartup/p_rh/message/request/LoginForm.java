package com.smartup.p_rh.message.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Modele de formulaire de login")
public class LoginForm {

	@ApiModelProperty(notes = "Le nom d'utilisateur")
	private String username;

	@ApiModelProperty(notes = "La mot de passe")
	private String pwd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}