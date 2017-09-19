package com.corgiking.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 解决跨域问题
 * 
 * @author SONGJC
 *
 */
public class InitCORSFilter extends OncePerRequestFilter {
	private Logger log = Logger.getLogger(InitCORSFilter.class);

	public InitCORSFilter() {
		log.info("=== 允许系统跨域请求 ===");
	}

	/**
	 * 解决跨域：Access-Control-Allow-Origin，值为*表示服务器端允许任意Domain访问请求
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", " Content-Type, Accept, ACCESS_TOKEN");
		response.addHeader("Access-Control-Max-Age", "1800");// 30 min
		response.addHeader("Access-Control-Allow-Credentials", "true");
		filterChain.doFilter(request, response);
	}

}
