package com.yy.api;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.org.apache.regexp.internal.recompile;
import com.yy.dao.basicinfo.entity.User;
import com.yy.service.UserService;

@RestController
@RequestMapping(value="/user")//,produces="application/json; charset=UTF-8"
public class UserInfoApi {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/name/{account_id}")
	public String getUserName(@PathVariable String account_id){
		String name = userService.getUserName(account_id);
		return name;
	}
	
	@GetMapping("/{account_id}")
	public User getUserInfo(@PathVariable Long account_id){
		User user = userService.getUserInfo(account_id);
		return user;
	}
	
	@GetMapping("/test/{id}")
	public HashMap<String, String> get(@PathVariable Long id){
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "yy");
		return map;
	}
}
