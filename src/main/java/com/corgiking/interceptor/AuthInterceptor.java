package com.corgiking.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.corgiking.exception.CustomException;
import com.corgiking.exception.ErrorCode;
import com.corgiking.interceptor.auth.AccountIdentity;
import com.corgiking.interceptor.auth.Auth;
import com.corgiking.interceptor.auth.Permission;

//@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = Logger.getLogger(AuthInterceptor.class);

	@Autowired
	private TokenCheck tokenCheck;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String[] notFilter = new String[] { "/login", "swagger", "v2/api-docs" }; // 不过滤的uri
		String uri = request.getRequestURI(); // 请求的uri
		for (String s : notFilter) {
			if (uri.indexOf(s) != -1) { // 如果uri中包含不过滤的uri，则不进行过滤
				return true;
			}
		}

		// 此处开始权限校验
		// token校验,并获取身份信息
		AccountIdentity identity = tokenCheck.getAccountIdentity(request, response);

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			Auth auth = ((HandlerMethod) handler).getMethodAnnotation(Auth.class);

			// 没有声明需要权限,或者声明不验证权限
			if (auth == null) {
				return true;
			}
			//方法上的权限
			log.info("AUTH:" + auth.permissions());
			Permission permissions = auth.permissions();

			// 判断用户所对应的权限
			boolean isPer = false;
			for (String per : identity.getPermissionList()) {
				if (per.equals(permissions.name())) {
					isPer = true;
					break;
				}
			}
			if (isPer) {
				log.info("success");
				return true;
			} else {
				log.info("no permissions");
				throw new CustomException(ErrorCode.UNAUTHORIZED_ACCESS, "无访问权限");
			}

		} else {
			log.info("get class error");
			return true;
		}
	}

	// private Set<String> getAllowedPermissions(String[] permissions) {
	// Set<String> allowedPermissions =
	// Arrays.asList(permissions).stream().collect(toSet());
	// return allowedPermissions;
	// }
}
