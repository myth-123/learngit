package com.hbzl.dev.ibom.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbzl.dev.ibom.system.mapper.RightMapper;
import com.hbzl.dev.ibom.system.mapper.RoleMapper;
import com.hbzl.dev.ibom.system.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RightMapper rightMapper;
	
	public List<String> getUserRolesRightsByUserName(String userName) {
		List<String> rolesList = new ArrayList<>();
		rolesList = roleMapper.getUserRolesByUserName(userName);
		List<String> rightsList = rightMapper.getRoleRightByRoleId(rolesList);
		System.out.println("=====");
		return rightsList;
	}

}
