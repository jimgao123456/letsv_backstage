package com.letsv.daoImpl;

import com.letsv.dao.WordDao;
import com.letsv.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository("wordDao")
public class WordDaoImpl implements WordDao {
	private final MongoTemplate mongoTemplate;

	@Autowired
	public WordDaoImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public Word findWordByName(String name) {
		Query query=new Query(Criteria.where("word").is(name));
		Word word =  mongoTemplate.findOne(query , Word.class);
		return word;
	}

	@Override
	public void saveWord(Word word) {
		mongoTemplate.save(word);
	}
}
