package com.hbzl.dev.ibom.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {

	@GetMapping
	public String errorStr() {
		return "没有权限-----请找管理员赋权";
	}
}
