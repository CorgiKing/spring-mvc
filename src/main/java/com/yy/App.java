package com.yy;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value="/web")
public class App {

	@GetMapping("/name")
	public String getName(){
		return "杨杨";
	}
	
	@GetMapping("/html")
	public String getHtml(){
		return "index";
	}
	
	@GetMapping("/jsp")
	public String get(){
		return "page";
	}
	
	@GetMapping("/mv")
	public ModelAndView getPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("users/info");
        return mv;
	}
	
	@GetMapping("/test")
	public HashMap<String, String> gett(){
		HashMap<String, String> map = new HashMap<>();
		map.put("name", "yy");
		return map;
	}
}
