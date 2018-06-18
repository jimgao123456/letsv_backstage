package com.letsv.daoImpl;

import com.letsv.common.DES;
import com.letsv.dao.UserDao;
import com.letsv.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;


@Component
public class UserDaoImpl implements UserDao {

	private final MongoTemplate mongoTemplate;
	private static final String secretstr="asfnjadkgoiahgoiahdgaoligfa=3";
	@Autowired
	public UserDaoImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


	@Override
	public void saveUser(User user) {
		String username=user.getUserName();
		String password=user.getPassWord();
		String nickname=user.getNickname();
		byte[] result = DES.encrypt(username.getBytes(),secretstr);
		if (result==null) return;
		user.setUserName(new String(result));
		result = DES.encrypt(password.getBytes(),secretstr);
		if (result==null) return;
		user.setPassWord(new String(result));
		result = DES.encrypt(nickname.getBytes(),secretstr);
		if (result==null) return;
		user.setNickname(new String(result));
		mongoTemplate.save(user);
	}


	@Override
	public User findUserByUserName(String userName) {
		try {
			byte[] result=DES.encrypt(userName.getBytes(),secretstr);
			if(result==null) throw new Exception();
			userName=new String(result);
			Query query=new Query(Criteria.where("userName").is(userName));
			User user =  mongoTemplate.findOne(query , User.class);
			result=DES.decrypt(user.getUserName().getBytes(),secretstr);
			user.setUserName(new String(result));
			result=DES.decrypt(user.getPassWord().getBytes(),secretstr);
			user.setPassWord(new String(result));
			result=DES.decrypt(user.getNickname().getBytes(),secretstr);
			user.setNickname(new String(result));
			return user;
		} catch (Exception e) {
			return null;
		}


	}


	@Override
	public void updateUser(User user) {
		Query query=new Query(Criteria.where("id").is(user.getId()));
		Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
		//更新查询返回结果集的第一条
		mongoTemplate.updateFirst(query,update,User.class);
		//更新查询返回结果集的所有
		// mongoTemplate.updateMulti(query,update,UserEntity.class);
	}


	@Override
	public void deleteUserById(Long id) {
		Query query=new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query,User.class);
	}
}