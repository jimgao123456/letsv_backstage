package com.letsv.dao;


import java.util.List;

public interface TestDao {
	List<String> getTestList(String username);
	void addCorrectWord(String username,String word);
}
