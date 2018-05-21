package com.letsv.model;

import java.io.Serializable;
import java.util.Random;

public class User implements Serializable {
	private static final long serialVersionUID = -3258839839160856613L;
	private Long id;
	private String userName;
	private String passWord;
	public User(){

	}
	public User(String userName,String passWord){
		Random random=new Random();
		id=random.nextLong();
		this.userName=userName;
		this.passWord=passWord;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}