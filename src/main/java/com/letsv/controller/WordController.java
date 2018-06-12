package com.letsv.controller;

import com.letsv.model.Word;
import com.letsv.service.GroupService;
import com.letsv.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WordController {
	private final WordService wordService;
	private final GroupService groupService;
	@Autowired
	public WordController(WordService wordService,GroupService groupService){
		this.wordService=wordService;
		this.groupService=groupService;
	}
	@PostMapping("/studyword")
	public @ResponseBody Map<String,Object> getword(String word) {
		Map<String,Object> map= wordService.wordToJson(word);
		List<String> group=groupService.getGroupWord(word);
		map.put("memory",wordService.getGroupString(group));
		return map;
	}

	@PostMapping("/addword")
	public @ResponseBody Map<String,Object> addword(String word,String xml) {
		Word myword=new Word();
		myword.setWord(word);
		myword.setWordJson(xml);
		wordService.saveWord(myword);
		HashMap<String,Object> map=new HashMap<>();
		map.put("success","11111");
		return map;
	}
}
