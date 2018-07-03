package com.hbzl.dev.ibom.config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hbzl.dev.ibom.system.mapper.RoleMapper;
import com.hbzl.dev.ibom.system.mapper.UserMapper;
import com.hbzl.dev.ibom.system.service.UserService;

/**
 * 
 * @ClassName: JwtToken
 * @Description: 生成JwtToken类
 * @author zhousw
 * @date 2018年6月12日
 *
 */
@Component
public class JwtToken {
	/** token秘钥，请勿泄露，请勿随便修改 backups:ZLXT-iBOM */
	private static final String SECRET = "ZLXT-iBOM";
	/** token 过期时间: 500分钟 */
	public static final int calendarField = Calendar.MINUTE;
	public static final int calendarInterval = 500;


	@Resource
	private RoleMapper roleMapper;
	private static RoleMapper staticRoleMapper;
	@Resource
	private UserService userService;
	private static UserService staticUserService;
	
	@PostConstruct
	public void init() {
		staticRoleMapper = roleMapper;
		staticUserService = userService;
	}
	/**
	 * JWT生成Token.<br/>
	 * 
	 * JWT构成: header, payload, signature
	 * 
	 * @param user_id
	 *            登录成功后用户user_id, 参数user_id不可传空
	 */
	public static String createToken(String loginName) throws Exception {
		Date iatDate = new Date();
		// expire time
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(calendarField, calendarInterval);
		Date expiresDate = nowTime.getTime();

		// header Map
		Map<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");

		//获取用户角色
		List<String> rolesList = new ArrayList<>();
		rolesList = staticRoleMapper.getUserRolesByUserName(loginName);
		//获取登录用户权限		
		List<String> rightsList = new ArrayList<>();
		rightsList = staticUserService.getUserRolesRightsByUserName(loginName);
		// build token
		// param backups {iss:Service, aud:APP}
		String token = JWT.create().withHeader(map) // header
				.withIssuer("iBOM").withAudience("front").withIssuedAt(iatDate) // sign time
				.withExpiresAt(expiresDate) // expire time
				.withClaim("loginName", loginName).withArrayClaim("roles", rolesList.toArray(new String[0]))
				.withArrayClaim("rights", rightsList.toArray(new String[0]))
				.sign(Algorithm.HMAC256(SECRET)); // signature
		return token;
	}

	/**
	 * 解密Token
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Claim> verifyToken(String token) {
		DecodedJWT jwt = null;
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
			jwt = verifier.verify(token);
		} catch (Exception e) {
			// e.printStackTrace();
			// token 校验失败, 抛出Token验证非法异常
			return null;
		}
		return jwt.getClaims();
	}

	/**
	 * 根据Token获取user_id
	 * 
	 * @param token
	 * @return user_id
	 */
	public static Long getAppUID(String token) {
		Map<String, Claim> claims = verifyToken(token);
		Claim user_id_claim = claims.get("loginName");
		if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
			// token 校验失败, 抛出Token验证非法异常
		}
		return Long.valueOf(user_id_claim.asString());
	}

	public static void main(String[] args) {
		String user_id = UUID.randomUUID().toString();
		String ss = null;
		try {
			JwtToken jwtToken = new JwtToken();
			ss = jwtToken.createToken(user_id);
			System.out.println(ss);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Claim> map = verifyToken(ss);
		String[] roleArr = map.get("roles").asArray(String.class);
		System.out.println(roleArr[1]);
	}
}
