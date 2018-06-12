package com.letsv.daoImpl;

import com.letsv.dao.GroupDao;
import com.letsv.model.GroupUser;
import com.letsv.model.GroupWord;
import com.letsv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupDaoImpl implements GroupDao{
	private final MongoTemplate mongoTemplate;

	@Autowired
	public GroupDaoImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void saveGroup(String groupid,String word) {
		GroupWord groupWord=new GroupWord();
		groupWord.setId("g"+groupid);
		groupWord.setWord(word);
		mongoTemplate.save(groupWord);
	}

	@Override
	public void saveGroupPos(int groupid,String username) {
		GroupUser groupUser=new GroupUser();
		groupUser.setId("gu"+groupid);
		groupUser.setUsername(username);
		mongoTemplate.save(groupUser);
	}

	@Override
	public void updateGroupPos(String username, int group) {
		Query query=new Query(Criteria.where("username").is(username));
		Update update= new Update().set("tid", "gu"+group);
		//更新查询返回结果集的第一条
		mongoTemplate.updateFirst(query,update,GroupUser.class);
	}

	@Override
	public int getGroupPosByUsername(String username) {
		Query query=new Query(Criteria.where("username").is(username));
		GroupUser groupUser=mongoTemplate.findOne(query,GroupUser.class);
		String id=groupUser.getId().substring(2);
		return Integer.parseInt(id);
	}

	@Override
	public List<String> getGroup(int id) {
		Query query=new Query(Criteria.where("tid").is("g"+id));
		List<GroupWord> groupWords=mongoTemplate.find(query,GroupWord.class);
		List<String> retn=new ArrayList<>();
		for(GroupWord groupWord:groupWords){
			retn.add(groupWord.getWord());
		}
		return retn;
	}

	@Override
	public List<String> getGroup(String word) {
		Query query=new Query(Criteria.where("word").is(word));
		GroupWord groupWord=mongoTemplate.findOne(query,GroupWord.class);
		if(groupWord==null)
			return new ArrayList<>();
		String id=groupWord.getId().substring(1);
		return getGroup(Integer.parseInt(id));
	}
}
