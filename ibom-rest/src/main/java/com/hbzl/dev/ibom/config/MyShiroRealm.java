package com.hbzl.dev.ibom.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.hbzl.dev.ibom.common.util.ListAddUtil;
import com.hbzl.dev.ibom.system.mapper.UserMapper;
import com.hbzl.dev.ibom.system.model.User;

/**
 * 
 * @ClassName: MyShiroRealm
 * @Description: 自定义Shiro登录类
 * @author zhousw
 * @date 2018年6月12日
 *
 */
public class MyShiroRealm extends AuthorizingRealm {

	@Resource
	private UserMapper userMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = String.valueOf(principals.getPrimaryPrincipal());

		authorizationInfo.addStringPermission("findUser");
		authorizationInfo.addStringPermission("deleteUser");
		/*
		 * User user = userMapper.selectByUserCode(username); List<Role> roleInfos =
		 * roleMapper.selectByUserId(user.getCId());
		 * 
		 * for (Role role : roleInfos) { // 添加角色
		 * authorizationInfo.addRole(role.getCRoleCode()); List<Page> pageList =
		 * pageMapper.selectByRoleId(role.getCId()); for (Page page : pageList) { //
		 * 添加权限 authorizationInfo.addStringPermission(page.getCPageCode()); } }
		 */
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
		if (token.getPrincipal() == null) {
			return null;
		}
		// 获取用户信息
		String userName = token.getPrincipal().toString();
		String password = new String((char[]) token.getCredentials());
		List<String> list = ListAddUtil.listAddString("id_","user_name_","password_","real_name_","phone_num_","email_");
		User user = userMapper.verifyUser(list,userName, password);
		if (user == null) {
			throw new AuthenticationException("用户名或密码错误.");
		} else {
			// 这里验证authenticationToken和simpleAuthenticationInfo的信息
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, getName());
			return simpleAuthenticationInfo;
		}
	}

}
