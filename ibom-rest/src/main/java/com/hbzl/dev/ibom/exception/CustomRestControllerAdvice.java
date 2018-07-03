package com.hbzl.dev.ibom.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hbzl.dev.ibom.common.result.ResultGenerator;

@RestControllerAdvice
public class CustomRestControllerAdvice {
	
	private static final Logger log = LoggerFactory.getLogger(CustomRestControllerAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public String defultExcepitonHandler(Exception e) {
		String result = null;
		if( e instanceof BusinessException ){
			log.error("业务异常：",e.getMessage());
			BusinessException businessException = (BusinessException)e;
//			result =  " 业务异常："+businessException.getMessage();
			result =  ResultGenerator.genFailResult("操作失败!请联系管理员!").toString();
		}else {
			log.error(e.getMessage(),e);
			result = ResultGenerator.genFailResult("操作失败!请联系管理员!").toString();
		}
		return result;  
		
	}

}
