package com.letsv.service;

import java.util.List;

public interface GroupService {
	int getGroupID(String username);
	List<String> getGroupWord(int id);
	List<String> getGroupWord(String word);
	void addGroup(int id,String word);
	void updateGroupUser(String username);
}
