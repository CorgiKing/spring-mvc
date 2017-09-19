package com.corgiking.service.mapper;

import org.springframework.stereotype.Component;

import com.corgiking.api.requset_object.UserInfo;
import com.corgiking.dao.basicinfo.entity.User;

@Component
public class UserToUserInfo extends MapperBase {
	public UserToUserInfo() {

		mapperFactory.classMap(UserInfo.class, User.class)
				// 变量匹配
				// .field("hdNumber", "hd_number")
				// .field("businessModel", "business_model")
				// .field("businessStatus", "business_status")
				// .field("roomNumber", "room_number")

				.byDefault()// 其他成员变量按变量名匹配
				.register();

	}
}
