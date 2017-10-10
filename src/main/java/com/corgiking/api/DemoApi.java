package com.corgiking.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.corgiking.aop.PerformInterfac;
import com.corgiking.exception.CustomException;
import com.corgiking.exception.ErrorCode;
import com.corgiking.service.PerformService;
import com.corgiking.service.DemoService;
import com.corgiking.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value="/demo")
@Api(tags="Demo")
public class DemoApi {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private PerformInterfac performService;
	
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
	
	@PostMapping("/upload")
	public List<String> upload(HttpServletRequest request, 
			@ApiParam(allowableValues = "video,photo", required = true) @RequestParam String file_type){
		List<String> fileNameList = fileService.saveFile(file_type, request);
		return fileNameList;
	}
	
	@GetMapping("/aop")
	public String aop(){
		System.out.println("aop");
		return performService.perform();
	}
}
