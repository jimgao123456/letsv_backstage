package com.letsv.model;

import java.io.Serializable;

public class Word implements Serializable {
	private String word;
	private String wordJson;

	public String getWordJson() {
		return wordJson;
	}

	public void setWordJson(String wordJson) {
		this.wordJson = wordJson;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
