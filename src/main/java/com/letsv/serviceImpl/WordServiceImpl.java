package com.letsv.serviceImpl;

import com.letsv.dao.UserDao;
import com.letsv.dao.WordDao;
import com.letsv.model.Word;
import com.letsv.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WordService")
public class WordServiceImpl implements WordService {
	private final WordDao wordDao ;

	@Autowired
	public WordServiceImpl(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	@Override
	public Word getWord() {
		return null;
	}

	@Override
	public void saveWord() {

	}
}
