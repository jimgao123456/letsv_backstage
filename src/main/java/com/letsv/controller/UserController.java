package com.letsv.controller;

import com.letsv.service.GroupService;
import com.letsv.service.LoginService;
import com.letsv.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {
	private final RegisterService registerService;
	private final LoginService loginService;
	private final GroupService groupService;
	@Autowired
	public UserController(RegisterService registerService,LoginService loginService,GroupService groupService) {
		this.registerService = registerService;
		this.loginService=loginService;
		this.groupService=groupService;
	}
	@PostMapping("/login")
	public @ResponseBody Map<String,Object> login(String username, String password) {
		String result=loginService.login(username,password);
		Map<String, Object> map = new HashMap<>();
		if(result!=null) {
			map.put("state", 0);
			map.put("message", "登陆成功");
			map.put("nickname",result);
			int id=groupService.getGroupID(username);
			if(id>=0 && id<=1300)
				map.put("plan",0);
			else if(id<=1500)
				map.put("plan",1);
			else
				map.put("plan",2);
		}else{
			map.put("state", 1);
			map.put("message", "用户名或密码错误");
		}
		map.put("token", UUID.randomUUID().toString());
		return map;
	}

	@PostMapping("/register")
	public @ResponseBody Map<String,Object> register(String username, String password,String nickname) {
		int result=registerService.register(username,password,nickname);
		Map<String, Object> map = new HashMap<>();
		if(result==0) {
			map.put("state", 0);
			map.put("message", "注册成功");
		}else if(result==1){
			map.put("state", 1);
			map.put("message", "用户名已存在");
		}else if(result==2){
			map.put("state", 1);
			map.put("message", "密码强度过低");
		}else {
			map.put("state", 1);
			map.put("message", "服务器炸了");
		}
		map.put("token", UUID.randomUUID().toString());
		return map;
	}


	@RequestMapping("/json")
	@ResponseBody
	public Map<String,Object> json() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "Ryan");
		map.put("age", "18");
		map.put("sex", "man");
		return map;
	}
}
