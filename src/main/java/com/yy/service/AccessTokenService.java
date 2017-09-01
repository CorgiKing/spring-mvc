package com.yy.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.yy.interceptor.auth.AccessToken;
import com.yy.interceptor.auth.AccountIdentity;

@Service
public class AccessTokenService {

//	@Autowired
//	private JedisService jedisService;

	public AccessToken getAccessToken(String token) {
		AccessToken accessToken = new AccessToken();
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("admin");
		accessToken.setAccountRoles(roles);
		accessToken.setToken("test");
		return accessToken;
	}

	public AccountIdentity getAccountIdentity(String string) {
		//模拟获得admin身份
		AccountIdentity identity = new AccountIdentity();
		identity.setLoginFlag(true);
		//角色
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("admin");
		identity.setRoleCodeList(roles);
		//权限
		ArrayList<String> permissionList = new ArrayList<>();
		permissionList.add("select");
		permissionList.add("delete");
		permissionList.add("add");
		permissionList.add("update");
		identity.setPermissionList(permissionList);
		return identity;
	}

	/**
	 * 从缓存中获取用户登录信息
	 * 
	 * @param token
	 * @return
	 */
//	public AccountIdentity getCacheUserLoginInfo(String token) {
//		return jedisService.get(token, AccountIdentity.class);
//	}
}
