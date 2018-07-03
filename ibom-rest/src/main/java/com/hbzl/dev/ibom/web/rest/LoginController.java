package com.hbzl.dev.ibom.web.rest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbzl.dev.ibom.common.result.ResultCode;
import com.hbzl.dev.ibom.common.result.ResultGenerator;
import com.hbzl.dev.ibom.config.JwtToken;
import com.hbzl.dev.ibom.config.PasswordEncryptConfig;
import com.hbzl.dev.ibom.exception.BusinessException;
import com.hbzl.dev.ibom.system.model.User;

/**
 * 登录验证接口
 * 
 * @ClassName: LoginController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhousw
 * @date 2018年6月12日
 *
 */

@RestController
public class LoginController {

	/*
	 * @GetMapping("/login") public String login(String loginName, String password)
	 * {
	 * 
	 * password = PasswordEncryptConfig.EncoderByMd5(password,loginName); Subject
	 * subject = SecurityUtils.getSubject(); UsernamePasswordToken token = new
	 * UsernamePasswordToken(loginName, password); String jwtToken = null; try {
	 * subject.login(token); //登录成功之后，获取用户角色，获取用户权限。 // String sessionId =
	 * subject.getSession().getId().toString(); UserBasic user = (UserBasic)
	 * subject.getPrincipals().getPrimaryPrincipal(); //生成一个token jwtToken =
	 * JwtToken.createToken(user.getLoginName()); } catch (Exception e) { //登录失败
	 * e.printStackTrace(); } return jwtToken; }
	 */
	/**
	 * 
	 * @Title: loginPost 
	 * @Description: 登录接口
	 * 前端请求方式设置application/x-www-form-urlencoded 
	 * @author zhousw 
	 * 返回类型 @throws
	 */
	@PostMapping("/login")
	public String loginPost(@RequestParam("userName") String userName, @RequestParam("password") String password) {

		password = PasswordEncryptConfig.EncoderByMd5(password, userName);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		String jwtToken = null;
		String result = null;
		try {
			subject.login(token);
			// 登录成功之后，获取用户角色，获取用户权限。
			// String sessionId = subject.getSession().getId().toString();
			User user = (User) subject.getPrincipals().getPrimaryPrincipal();
			// 生成一个token
			jwtToken = JwtToken.createToken(user.getUserName());
			result = ResultGenerator.genSuccessResult(jwtToken).toString();
		} catch (Exception e) {
			result = ResultGenerator.genFailResult("登录失败!用户名/密码错误!").toString();
			e.printStackTrace();
//			throw new BusinessException(4001,"用户名密码错误");
		}
		return result;
	}
}
