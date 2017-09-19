package com.corgiking.service;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corgiking.api.requset_object.UserInfo;
import com.corgiking.dao.basicinfo.entity.User;
import com.corgiking.dao.basicinfo.mapper.UserDao;
import com.corgiking.exception.CustomException;
import com.corgiking.exception.ErrorCode;
import com.corgiking.service.mapper.UserToUserInfo;

@Service
@Transactional(transactionManager="basicInfoTransactionManager")
public class UserService {
	private Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserToUserInfo userToUserInfo;

	public String getUserName(String account_id) {
		String name = userDao.getUserName(account_id);
		log.info(MessageFormat.format("查询account_id:{0},获得姓名:{1}", account_id,name));
		return name;
	}

	public UserInfo getUserInfo(Long account_id) {
		
//		UserExample example = new UserExample();
//		example.createCriteria().andAccountIdEqualTo(account_id);
//		List<User> users = userDao.selectByExample(example);
		
		User user = userDao.selectUserInfo(account_id);
		UserInfo userInfo = userToUserInfo.map(user, UserInfo.class);
		return userInfo;
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
