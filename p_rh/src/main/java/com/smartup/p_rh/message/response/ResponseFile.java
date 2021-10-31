package com.smartup.p_rh.message.response;

import com.smartup.p_rh.users.User;

public class ResponseFile {
	private String name;
	private String url;
	private String type;
	private long size;
	private User user;

	public ResponseFile(String name, String url, String type, long size, User user) {
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
