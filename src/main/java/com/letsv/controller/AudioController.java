package com.letsv.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AudioController {
	@PostMapping("/audio")
	public @ResponseBody Map<String,Object> login(int number) {

		Map<String, Object> map = new HashMap<>();
		String word="apple";
		switch (number){
			case 1:
				word="apple";
				break;
		}
		map.put("url","http://media.shanbay.com/audio/us/"+word+".mp3");
		return map;
	}
}
