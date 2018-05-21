package com.letsv.model;

import java.io.Serializable;

public class Word implements Serializable {
	private String word;
	private String word_meaning;
	private int rank=0;
	private String soundmark;
	private String pronunciation;
	private String picture;
	private String explanation;
	private String sentence;
	private String sentence_meaning;


	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getWord_meaning() {
		return word_meaning;
	}

	public void setWord_meaning(String word_meaning) {
		this.word_meaning = word_meaning;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getSoundmark() {
		return soundmark;
	}

	public void setSoundmark(String soundmark) {
		this.soundmark = soundmark;
	}

	public String getPronunciation() {
		return pronunciation;
	}

	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getSentence_meaning() {
		return sentence_meaning;
	}

	public void setSentence_meaning(String sentence_meaning) {
		this.sentence_meaning = sentence_meaning;
	}
}
