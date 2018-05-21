package com.letsv.dao;

import com.letsv.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {

	void saveUser(User user);

	User findUserByUserName(String userName);

	void updateUser(User user) ;
	void deleteUserById(Long id);
}