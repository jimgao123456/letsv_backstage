package com.letsv.serviceImpl;

import com.letsv.dao.GroupDao;
import com.letsv.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GroupService")
public class GroupServiceImpl implements GroupService{
	private final GroupDao groupDao;

	@Autowired
	public GroupServiceImpl(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public int getGroupID(String username) {
		return groupDao.getGroupPosByUsername(username);
	}

	@Override
	public List<String> getGroupWord(int id) {
		return groupDao.getGroup(id);
	}

	@Override
	public List<String> getGroupWord(String word) {
		return groupDao.getGroup(word);
	}

	@Override
	public void addGroup(int id, String word) {
		groupDao.saveGroup(""+id,word);
	}

	@Override
	public void updateGroupUser(String username) {
		int id=groupDao.getGroupPosByUsername(username);
		groupDao.updateGroupPos(username,id+1);
	}

	@Override
	public void changePlan(String username,int num) {
		int id=groupDao.getGroupPosByUsername(username);
		switch (num){
			case 0:
				groupDao.updateGroupPos(username,0);
				break;
			case 1:
				groupDao.updateGroupPos(username,1301);
				break;
			case 2:
				groupDao.updateGroupPos(username,1501);
				break;
		}

	}


}
