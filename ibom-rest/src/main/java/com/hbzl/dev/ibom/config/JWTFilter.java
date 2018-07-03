package com.hbzl.dev.ibom.config;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.interfaces.Claim;

/**
 * 
 * @ClassName: JWTFilter
 * @Description: JwtToken校验拦截器
 * @author zhousw
 * @date 2018年6月13日
 *
 */
public class JWTFilter extends AccessControlFilter {
	private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("======onAccessDenied======开始自定义======拦截=====");
		saveRequestAndRedirectToLogin(request, response);
		return false;
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// 在这个拦截器里加入校验jwt token的方法
		System.out.println("======isAccessAllowed======开始自定义======拦截=====");
		boolean bool = false;
		Subject subject = getSubject(request, response);
		if (subject.isAuthenticated()) {
			// 已登录
			// 校验jwt
			String jwtToken = ((HttpServletRequest) request).getHeader("Authorization");
			if (null == jwtToken) {
				return bool;
			} else {
				Map<String, Claim> map = JwtToken.verifyToken(jwtToken);
				if (null == map) {
					return bool;
				} else {
					bool = true;
				}
			}
		} else {
			return bool;
		}
		/*
		 * if(subject.isAuthenticated()) { HttpServletResponse httpServletResponse =
		 * (HttpServletResponse) response; httpServletResponse.sendRedirect("/401");
		 * WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED); }
		 */
		return bool;
	}

}
