package com.corgiking.dao.basicinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.corgiking.dao.basicinfo.entity.User;

public interface UserDao extends UserMapper{

	public String getUserName(@Param("account_id") String account_id);

	public User selectUserInfo(@Param("account_id") Long account_id);

	public List<User> select(@Param("from_index") Integer fromindex, @Param("to_index") Integer toindex);

}
