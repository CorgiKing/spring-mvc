package com.corgiking.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corgiking.dao.basicinfo.entity.User;
import com.corgiking.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/*
 * 在swagger-annotations jar包中 1.5.X版本以上, 注解 io.swagger.annotations.API 
 * 中的description被废弃了。新的swagger组件中使用了新的方法来对Web api 进行分组。原来使用 description ，
 * 默认一个Controller类中包含的方法构成一 个api分组。现在使用tag，可以更加方便的分组。
 * 比如把两个Controller类里的方法划分成同一个分组。tag的key用来区分不同的分组。tag的value用做分组的描述。
 * @ApiOperation 中value是api的简要说明，在界面api 链接的右侧，少于120个字符。
 * @ApiOperation 中notes是api的详细说明，需要点开api 链接才能看到。
 * @ApiOperation 中 produces 用来标记api返回值的具体类型。这里是json格式，utf8编码。
 */


@RestController
@RequestMapping(value="/user")
@Api(tags="User")
public class UserInfoApi {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/name/{account_id}")
	@ApiOperation(value="获取用户姓名",notes="根据账号获取用户姓名")
	public String getUserName(@PathVariable String account_id){
		String name = userService.getUserName(account_id);
		return name;
	}
	
	@GetMapping("/{account_id}")
	public User getUserInfo(@PathVariable Long account_id){
		User user = userService.getUserInfo(account_id);
		return user;
	}
	
	@PostMapping
	public boolean addUser(@RequestBody User user){
		boolean s = userService.addUser(user);
		return s;
	}
	
	@GetMapping
	public List<User> getUserList(@ApiParam(value = "开始位置", required = true) @RequestParam Integer offset,
			@ApiParam(value = "数据数量", required = true) @RequestParam Integer count){
		List<User> users = userService.getUserList(offset,count);
		return users;
	}
}
