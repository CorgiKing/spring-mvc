package com.corgiking.interceptor;

import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.corgiking.exception.CustomException;
import com.corgiking.exception.ErrorCode;
import com.corgiking.interceptor.auth.AccountIdentity;
import com.corgiking.service.AccessTokenService;

/**
 * 接口权限过滤
 * 
 */
@Component
public class TokenCheck {
	private static Logger log = Logger.getLogger(TokenCheck.class);

	@Autowired
	private AccessTokenService accessTokenService;

	protected AccountIdentity getAccountIdentity(HttpServletRequest request, HttpServletResponse response) {

		// 执行过滤,从head中获取token值。
		String token = request.getHeader("ACCESS_TOKEN");
		log.info(MessageFormat.format("token:{0}", token));

		AccountIdentity userLoginInfo = null;

		if (StringUtils.isNotEmpty(token)) {
			// 从缓存中获取token
			userLoginInfo = accessTokenService.getAccountIdentity(token);

			if (userLoginInfo == null) {
				log.info("Token is expired, deny access: " + token);
				throw new CustomException(ErrorCode.TOKEN_EXPIRED, "token过期");
			}

			if (userLoginInfo.isLoginFlag()) {
				log.info("Account is login at other place, deny access: " + token);
				throw new CustomException(ErrorCode.TOKEN_CANCELED_BY_OTHERS, "该账号在其他地方登录");
			}

		} else {
			log.info("用户未登录");
			throw new CustomException(ErrorCode.TOKEN_MISS, "用户未登录");
		}
		return userLoginInfo;
	}

	public void response(HttpServletRequest request, HttpServletResponse response, ErrorCode responseCode) {
		try {
			response.setCharacterEncoding("UTF-8");// 设置request和response的字符集，防止乱码
			response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			PrintWriter out = response.getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", responseCode.toString());
			map.put("errorcode", responseCode.getCode());
			Gson gson = new Gson();
			out.print(gson.toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
