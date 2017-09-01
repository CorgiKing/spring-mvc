package com.yy.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yy.exception.CustomException;
import com.yy.exception.ErrorCode;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/demo")
@Api(tags="Demo")
public class DemoApi {
	
	@GetMapping("/exception")
	public void testException(){
		throw new CustomException(ErrorCode.REQUEST_TYPE_ERROR, "测试统一异常处理");
	}
}
