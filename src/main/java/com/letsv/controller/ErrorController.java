package com.letsv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	@GetMapping("/error")
	public String index(){
		return "403.html"; //当浏览器输入/index时，会返回 /templates/home.html页面
	}
}
