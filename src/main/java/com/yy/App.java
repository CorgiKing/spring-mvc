package com.yy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class App {

	@GetMapping("/get")
	public String getName(){
		return "yy";
	}
	
	@GetMapping("/index")
	public String getHtml(){
		return "index";
	}
	
	@GetMapping("/page")
	public String get(){
		return "page";
	}
}
