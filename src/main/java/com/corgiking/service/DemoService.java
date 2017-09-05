package com.corgiking.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
	
	@PostConstruct
	public void init(){
		System.out.println("---------初始化bean------------");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("---------销毁bean--------------");
	}
}
