package com.letsv.dao;

import com.letsv.model.GroupUser;
import com.letsv.model.GroupWord;

import java.util.List;

public interface GroupDao {
	void saveGroup(String groupid,String word);
	void saveGroupPos(int groupid,String username);
	void updateGroupPos(String username,int group);
	int getGroupPosByUsername(String username);
	List<String> getGroup(int id);
	List<String> getGroup(String word);

}
