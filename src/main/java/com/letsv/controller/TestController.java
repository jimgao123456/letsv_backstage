package com.letsv.controller;


import com.letsv.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
	private final TestService testService;

	@Autowired
	public TestController(TestService testService) {
		this.testService = testService;
	}

	@PostMapping("/gettestlist")
	public @ResponseBody Map<String,Object> getTestList(String username) {
		Map<String,Object> map=new HashMap<>();
		List<String> list=testService.getTestList(username);
		StringBuilder stringBuilder=new StringBuilder();
		for(String str:list){
			stringBuilder.append(str).append(" ");
		}
		map.put("list",stringBuilder.toString().trim());
		return map;
	}

	@PostMapping("/correctword")
	public @ResponseBody Map<String,Object> correctWord(String username,String word) {
		Map<String,Object> map=new HashMap<>();
		testService.addCorrectWord(username,word);
		map.put("success",0);
		return map;
	}
}
