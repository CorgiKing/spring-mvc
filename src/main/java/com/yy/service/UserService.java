package com.yy.service;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.dao.basicinfo.entity.User;
import com.yy.dao.basicinfo.mapper.UserDao;
import com.yy.exception.CustomException;
import com.yy.exception.ErrorCode;

@Service
@Transactional(transactionManager="basicInfoTransactionManager")
public class UserService {
	private Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;

	public String getUserName(String account_id) {
		String name = userDao.getUserName(account_id);
		log.info(MessageFormat.format("查询account_id:{0},获得姓名:{1}", account_id,name));
		return name;
	}

	public User getUserInfo(Long account_id) {
		User user = userDao.selectUserInfo(account_id);
		return user;
	}

	public boolean addUser(User user) {
		int ret = userDao.insert(user);
		if (ret != 1) {
			throw new CustomException(ErrorCode.INSER_ERROR	, "用户信息保存失败");
		}
		log.info(MessageFormat.format("用户信息插入成功:{0}", user));
		return true;
	}

	public List<User> getUserList(Integer offset, Integer count) {
		List<User> users = userDao.select(offset,offset+count);
		return users;
	}

}
