package com.smartup.p_rh.mail;

import io.swagger.annotations.ApiModelProperty;

public class Mail {
	
	@ApiModelProperty(value = "Mail sender address")
	public String MAIL_FROM ;

	@ApiModelProperty(value = "user receiver id")
	private Integer idUser;

	@ApiModelProperty(value = "Email subject")
	private String emailSubject;

	@ApiModelProperty(value = "Email content")
	private String emailContent;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent(String nom, String pwd) {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	public String getMAIL_FROM() {
		return MAIL_FROM;
	}

	@Override
	public String toString() {
		return "Mail [MAIL_FROM=" + MAIL_FROM + ", idUser=" + idUser + ", emailSubject=" + emailSubject
				+ ", emailContent=" + emailContent + "]";
	}
	
	

}
