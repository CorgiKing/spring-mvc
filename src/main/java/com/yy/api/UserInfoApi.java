package com.yy.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoApi {
	
	@GetMapping("/name/{id}")
	public String getUserName(String id){
		return "YY";
	}
	
	public void st(){
		//insert ����
		
		//������־insert����
		
		//�����״̬update
		
		//������־insert
	}
}
