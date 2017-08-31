package com.yy.dao.basicinfo.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserDao {

	public String getUserName(@Param("account_id") String account_id);

}
