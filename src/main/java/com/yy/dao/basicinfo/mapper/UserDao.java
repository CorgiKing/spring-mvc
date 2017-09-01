package com.yy.dao.basicinfo.mapper;

import org.apache.ibatis.annotations.Param;

import com.yy.dao.basicinfo.entity.User;

public interface UserDao extends UserMapper{

	public String getUserName(@Param("account_id") String account_id);

	public User selectUserInfo(@Param("account_id") Long account_id);

}
