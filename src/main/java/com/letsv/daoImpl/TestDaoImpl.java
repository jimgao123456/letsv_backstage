package com.letsv.daoImpl;

import com.letsv.dao.TestDao;
import com.letsv.model.User;
import com.letsv.model.Userword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TestDaoImpl implements TestDao {
	private final MongoTemplate mongoTemplate;

	@Autowired
	public TestDaoImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	@Override
	public List<String> getTestList(String username) {
		List<String> list=new ArrayList<>();
		Query query=new Query(Criteria.where("username").is(username).and("time").lte(3));
		List<Userword> userwordList=mongoTemplate.find(query,Userword.class);
		for(Userword userword:userwordList){
			list.add(userword.getWord());
		}
		return list;
	}

	@Override
	public void addCorrectWord(String username, String word) {
		Query query=new Query(Criteria.where("username").is(username).and("word").is(word));
		Userword user =  mongoTemplate.findOne(query , Userword.class);
		if(user==null){
			Userword userword=new Userword();
			userword.setTime(0);
			userword.setUsername(username);
			userword.setWord(word);
			mongoTemplate.save(userword);
		}
		else {
			Update update= new Update().set("time",user.getTime()+1);
			//更新查询返回结果集的第一条
			mongoTemplate.updateFirst(query,update,Userword.class);
		}
	}
}
