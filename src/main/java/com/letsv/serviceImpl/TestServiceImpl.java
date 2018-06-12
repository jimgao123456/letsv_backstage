package com.letsv.serviceImpl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.letsv.dao.TestDao;
import com.letsv.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("TestService")
public class TestServiceImpl implements TestService{
	private final TestDao testDao;

	@Autowired
	public TestServiceImpl(TestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public List<String> getTestList(String username) {
		return testDao.getTestList(username);
	}

	@Override
	public void addCorrectWord(String username, String word) {
		testDao.addCorrectWord(username,word);
	}
}
