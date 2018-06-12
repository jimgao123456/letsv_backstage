package com.letsv.model;

import java.io.Serializable;
import java.util.UUID;

public class GroupUser implements Serializable {
	private String id;
	private String tid;
	private String username;

	public GroupUser(){
		this.id= UUID.randomUUID().toString();
	}
	public String getId() {
		return tid;
	}

	public void setId(String id) {
		this.tid = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
