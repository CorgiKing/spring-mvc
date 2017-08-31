package com.yy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yy.service.UserService;

@RestController
@RequestMapping("/user")
public class UserInfoApi {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/name/{id}")
	public String getUserName(String account_id){
		String name = userService.getUserName(account_id);
		return name;
	}
	
	public void st(){
		
	}
}
