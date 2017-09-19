package com.corgiking.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.corgiking.exception.CustomException;
import com.corgiking.exception.ErrorCode;
import com.corgiking.service.DemoService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/demo")
@Api(tags="Demo")
public class DemoApi {
	
	@Autowired
	private DemoService demoService;
	
	@GetMapping("/exception")
	public void testException(){
		throw new CustomException(ErrorCode.REQUEST_TYPE_ERROR, "测试统一异常处理");
	}
	
	@GetMapping("/redirect")
	public ModelAndView redirect(){
		String url = "http://www.baidu.com.cn/";
		
		ModelAndView mav = new ModelAndView();
		mav.setStatus(HttpStatus.FOUND);//302
		mav.setViewName("redirect:"+url);
		
		mav.addObject("user", "Corgi King");
		
		return mav;
	}
}
