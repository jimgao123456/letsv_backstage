package com.letsv.serviceImpl;

import com.letsv.dao.UserDao;
import com.letsv.model.User;
import com.letsv.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("RegisterService")
public class RegisterServiceImpl implements RegisterService{
	private final UserDao userDao;

	@Autowired
	public RegisterServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int register(String username, String password) {
		User user=userDao.findUserByUserName(username);
		if(user!=null) return 1;
		Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
		Matcher m = pattern.matcher(password);
		if(!m.matches()) return 2;
		userDao.saveUser(new User(username,password));
		return 0;
	}
}
