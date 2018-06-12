package com.letsv.service;

import com.letsv.model.Word;

import java.util.Map;

public interface WordService {
	String getWord(String word);
	void saveWord(Word word);
	Map<String,Object> wordToJson(String word);
}
