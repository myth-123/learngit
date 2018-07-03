package com.hbzl.dev.ibom.exception;

/**
 * 
 * @ClassName: CustomException
 * @Description: 业务异常类
 * @author zhousw
 * @date 2018年6月12日
 *
 */
public class BusinessException extends RuntimeException{  
    
    private static final long serialVersionUID = 1L;  
      
    private Integer code;  //错误码  
      
    public BusinessException(int code,String message) {
        super(message);
        this.code = code;
    }
    
    public BusinessException(ExceptionResultEnum resultEnum) {  
        super(resultEnum.getMsg());  
        this.code = resultEnum.getCode();  
    }  
      
    public Integer getCode() {  
        return code;  
    }  
  
    public void setCode(Integer code) {  
        this.code = code;  
    }  
}  
