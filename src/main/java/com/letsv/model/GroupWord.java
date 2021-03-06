package com.letsv.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class GroupWord implements Serializable{
	private String id;
	private String tid;
	private String word;
	public GroupWord(){
		this.id= UUID.randomUUID().toString();
	}
	public String getId() {
		return tid;
	}

	public void setId(String id) {
		this.tid = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
