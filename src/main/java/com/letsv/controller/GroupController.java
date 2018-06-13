package com.letsv.controller;

import com.letsv.service.GroupService;
import com.letsv.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class GroupController {
	private final GroupService groupService;
	private final WordService wordService;
	@Autowired
	public GroupController(GroupService groupService,WordService wordService){
		this.groupService=groupService;
		this.wordService=wordService;
	}

	@PostMapping("/addgroup")
	public @ResponseBody Map<String,Object> addGroup(String id, String word) {
		Map<String,Object> map=new HashMap<>();
		int success=0;
		try {
			int groupid=Integer.parseInt(id);
			groupService.addGroup(groupid,word);
		}catch (Exception e){
			success=1;
		}
		map.put("success",success);
		return map;
	}

	@PostMapping("/wordgrouplist")
	public @ResponseBody Map<String,Object> getGroup(String username){
		int id=groupService.getGroupID(username);
		List<String> group=groupService.getGroupWord(id);
		String s=wordService.getGroupString(group);
		Map<String,Object> map=new HashMap<>();
		map.put("words",s);
		map.put("memory","相似单词组");
		return map;
	}

	@PostMapping("/updategroup")
	public @ResponseBody Map<String,Object> updateGroup(String username){
		groupService.updateGroupUser(username);
		Map<String,Object> map=new HashMap<>();
		map.put("success","0");
		return map;
	}

	@PostMapping("/changeplan")
	public @ResponseBody Map<String,Object> updateGroup(String username,int level){
		groupService.changePlan(username,level);
		Map<String,Object> map=new HashMap<>();
		map.put("success","0");
		return map;
	}

	@PostMapping("testword")
	public @ResponseBody Map<String,Object> testWord(String word){
		Map<String,Object> map=new HashMap<>();
		List<String> group=groupService.getGroupWord(word);
		Map<String,String> explanation=wordService.getGroupMeaning(group);
		String wordexplan=explanation.get(word);
		if(wordexplan==null) {
			wordexplan="";
		}else {
			explanation.remove(word);
		}
		Random random=new Random();
		int rightans=random.nextInt(4);
		List<String> explan=new ArrayList<>();
		for(String i:explanation.keySet()){
			explan.add(explanation.get(i));
		}
		if(explan.size()<4){
			for(int i=explan.size();i<4;i++){
				explan.add("这是一个选项");
			}
		}
		Collections.shuffle(explan);
		String[] s=new String[explan.size()];
		explan.toArray(s);
		s[rightans]=wordexplan;
		map.put("word",word);
		map.put("music","http://media.shanbay.com/audio/us/"+word+".mp3");
		map.put("0",s[0]);
		map.put("1",s[1]);
		map.put("2",s[2]);
		map.put("3",s[3]);
		map.put("correct", ""+rightans);
		return map;
	}
}
