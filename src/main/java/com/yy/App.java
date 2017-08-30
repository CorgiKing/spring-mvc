package com.yy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/web")
public class App {

	@GetMapping("/name")
	@ResponseBody
	public String getName(){
		return "yy";
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
}
