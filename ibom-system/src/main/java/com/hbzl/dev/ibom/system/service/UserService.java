package com.hbzl.dev.ibom.system.service;

import java.util.List;
import java.util.Map;

public interface UserService {
	
	//获取用户角色
	//获取用户权限
	public List<String> getUserRolesRightsByUserName(String userName);
}
