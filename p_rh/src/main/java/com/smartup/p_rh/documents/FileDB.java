package com.smartup.p_rh.documents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.smartup.p_rh.users.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "files")
@ApiModel(value = "Modele des documents administratifs")
public class FileDB {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@ApiModelProperty(notes = "Id de document")
	@Column(name = "id")
	private String id;

	@ApiModelProperty(notes = "Le nom du document")
	@Column(name = "name")
	private String name;

	@ApiModelProperty(notes = "Le type du document")
	@Column(name = "type")
	private String type;

	@Lob
	@Column(name = "data")
	private byte[] data;

	@ApiModelProperty(notes = "L'id de l'utilisateur")
	@ManyToOne
	@JoinColumn(name = "users_login")
	private User user;

	public FileDB() {
	}

	public FileDB(String name, String type, byte[] data) {
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
