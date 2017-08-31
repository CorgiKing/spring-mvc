package com.yy.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yy.dao.basicinfo.mapper.UserDao;

@Service
public class UserService {
	private Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;

	public String getUserName(String account_id) {
		String name = userDao.getUserName(account_id);
		log.info(name);
		return name;
	}

}
