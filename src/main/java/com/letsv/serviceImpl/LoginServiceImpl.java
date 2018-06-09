package com.letsv.serviceImpl;

import com.letsv.dao.UserDao;
import com.letsv.model.User;
import com.letsv.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {
	private final UserDao userDao;

	@Autowired
	public LoginServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public String login(String username, String password) {
		User user = userDao.findUserByUserName(username);
		if (user != null && user.getPassWord().equals(password))
			return user.getNickname();
		else
			return null;
	}
}
