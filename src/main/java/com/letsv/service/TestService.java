package com.letsv.service;

import java.util.List;

public interface TestService {
	List<String> getTestList(String username);
	void addCorrectWord(String username,String word);
}
