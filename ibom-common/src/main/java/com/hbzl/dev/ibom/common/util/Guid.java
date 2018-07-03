package com.hbzl.dev.ibom.common.util;

import java.util.UUID;

public class Guid {
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().toUpperCase().trim()
				.replaceAll("-", "");
		return uuid;
	}
}
