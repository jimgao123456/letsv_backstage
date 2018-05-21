package com.letsv.daoImpl;

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

	@Autowired
	public UserDaoImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * 创建对象
	 * @param user
	 */
	@Override
	public void saveUser(User user) {
		mongoTemplate.save(user);
	}

	/**
	 * 根据用户名查询对象
	 * @param userName
	 * @return
	 */
	@Override
	public User findUserByUserName(String userName) {
		Query query=new Query(Criteria.where("userName").is(userName));
		User user =  mongoTemplate.findOne(query , User.class);
		return user;
	}

	/**
	 * 更新对象
	 * @param user
	 */
	@Override
	public void updateUser(User user) {
		Query query=new Query(Criteria.where("id").is(user.getId()));
		Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
		//更新查询返回结果集的第一条
		mongoTemplate.updateFirst(query,update,User.class);
		//更新查询返回结果集的所有
		// mongoTemplate.updateMulti(query,update,UserEntity.class);
	}

	/**
	 * 删除对象
	 * @param id
	 */
	@Override
	public void deleteUserById(Long id) {
		Query query=new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query,User.class);
	}
}