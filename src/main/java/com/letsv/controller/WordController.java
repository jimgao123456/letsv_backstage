package com.letsv.controller;

import com.letsv.model.Word;
import com.letsv.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WordController {
	private final WordService wordService;
	@Autowired
	public WordController(WordService wordService){
		this.wordService=wordService;
	}
	@PostMapping("/studyword")
	public @ResponseBody Map<String,Object> getword(String word) {
		return wordService.wordToJson(word);
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
