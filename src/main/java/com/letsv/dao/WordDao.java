package com.letsv.dao;

import com.letsv.model.User;
import com.letsv.model.Word;
import org.springframework.stereotype.Component;


public interface WordDao {

	Word findWordByName(String name);
	void saveWord(Word word);
}
