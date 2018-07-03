package com.hbzl.dev.ibom.common.util;

import java.util.ArrayList;
import java.util.List;

public class ListAddUtil {

	public static List<String> listAddString(String...strings ){
		List<String> list = new ArrayList<>();
		for(String s:strings) {
			list.add(s);
		}
		return list;
	}
}
