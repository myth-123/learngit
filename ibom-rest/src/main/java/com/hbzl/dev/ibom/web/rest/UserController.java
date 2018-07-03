package com.hbzl.dev.ibom.web.rest;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hbzl.dev.ibom.common.result.ResultGenerator;
import com.hbzl.dev.ibom.common.util.Guid;
import com.hbzl.dev.ibom.config.PasswordEncryptConfig;
import com.hbzl.dev.ibom.system.mapper.UserMapper;
import com.hbzl.dev.ibom.system.model.User;

@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserMapper userMapper;
	
	@PostMapping("saveUser")
	public String saveUser(User user) {
		user.setId(Guid.getUUID());
		user.setPassword(PasswordEncryptConfig.EncoderByMd5(user.getPassword(),user.getUserName()));
		userMapper.insert(user);
		return ResultGenerator.genSuccessResult("保存用户成功!", null).toString();
	}
	@PostMapping("getUser/{loginName}")
	public String getUser(@PathVariable("loginName") String loginName,@RequestParam("param")String param ) {
		Map map = JSON.parseObject(param);
		System.out.println(loginName);
		System.out.println(param);
		return loginName;
	}
//	@PostMapping("getUserTest/{loginName}")
//	public String getUserTest(@PathVariable("loginName") String loginName) {
//		List<String> list = new ArrayList<String>();
//		list.add("login_name_ as loginName");
//		list.add("password_ as password");
//		list.add("email_ as email");
//		list.add("date as date");
//		list.add("aa_ as aa");
//		Map<String,Object> userBasic = userBasicMapper.getUser(list,loginName);
//		String s = ResultGenerator.genSuccessResult(userBasic).toString();
//		return s;
//	}
	
	
//	@Resource
//	private UserMapper userMapper;

//	@GetMapping("/findUser/{loginName}")
//	public String findUserByLoginName(@PathVariable("loginName") String loginName, HttpServletRequest request,
//			HttpServletResponse reponse) {
//		String jwtToken = request.getHeader("Authorization");
//		if (null == jwtToken) {
//			return "请求失败 401";
//		} else {
//			// 校验
//			Map<String, Claim> map = JwtToken.verifyToken(jwtToken);
//			Claim claim = map.get("user_id");
//
//			Date expDate = map.get("exp").asDate();
//			User user = userMapper.selectUserByLoginName(loginName);
//			return "查找的用户为： " + user.getRealName() + " 邮箱:" + user.getEmail() + "登录名为：" + user.getLoginName();
//		}
//	}
//
//	@PostMapping("/findUser/{loginName}")
//	public String findUser(@PathVariable("loginName") String loginName, HttpServletRequest request,
//			HttpServletResponse reponse) {
//		String jwtToken = request.getHeader("Authorization");
//		if (null == jwtToken) {
//			return "请求失败 401";
//		} else {
//			// 校验
//			Map<String, Claim> map = JwtToken.verifyToken(jwtToken);
//			Date expDate = map.get("exp").asDate();
//			User user = userMapper.selectUserByLoginName(loginName);
//			return "POST请求 ：查找的用户为： " + user.getRealName() + " 邮箱:" + user.getEmail() + "登录名为：" + user.getLoginName();
//		}
//
//	}
//
//	@GetMapping("/setUser/{loginName}")
//	@RequiresPermissions("getUser")
//	public String setUser(@PathVariable("loginName")String loginName,HttpServletRequest request,HttpServletResponse reponse) {
//		User user = userMapper.selectUserByLoginName(loginName);
//		return "POST请求 ：查找的用户为： "+user.getRealName()+" 邮箱:"+user.getEmail()+"登录名为："+user.getLoginName();
//	}
}
