package com.hbzl.dev.ibom.config;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @ClassName: PasswordEncryptConfig
 * @Description: 用户密码加密配置
 * @author zhousw
 * @date 2018年6月13日
 *
 */
public class PasswordEncryptConfig {
	/**
	 * 利用MD5加密，99次。
	 * 
	 * @param str
	 *            初始密码
	 * @param salt
	 *            加密盐。为用户名
	 * @return
	 */
	public final static String EncoderByMd5(String str, String salt) {
		// 下边代码中的99，和spring-mybatis.xml中的<property name="hashIterations"
		// value="99"></property>value对应，要不shiro无法匹配验证
		String md5 = new Md5Hash(str, salt, 99).toString();
		return md5;
	}

	public static void main(String args[]) {
		String originPassword = "123456";
		String salt = "zhoushiwei";
		String md5 = new Md5Hash(originPassword, salt, 99).toString();
		System.out.println(md5);
	}
}
