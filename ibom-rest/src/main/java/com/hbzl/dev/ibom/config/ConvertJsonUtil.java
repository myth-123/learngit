package com.hbzl.dev.ibom.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.hbzl.dev.ibom.system.model.User;

public class ConvertJsonUtil {
	/**
     * 将对象的大写转换为下划线加小写，例如：userName-->user_name
     * 
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public static String toUnderlineJSONString(Object object) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(Include.NON_NULL);      
        String reqJson = mapper.writeValueAsString(object);
        return reqJson;
    }

    /**
     * 将下划线转换为驼峰的形式，例如：user_name-->userName
     * 
     * @param json
     * @param clazz
     * @return
     * @throws IOException
     */
    public static <T> T toSnakeObject(String json, Class<T> clazz) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        T reqJson =  mapper.readValue(json, clazz);
        return reqJson;
    }
    
    public static String map2jsonSnakeCase(Map<String,Object> map) {
    	String jsonStr = "";
    	for(String key:map.keySet()) {
    		
    	}
		return null;
    	
    }
    public static void main(String[] args) {
    	 String json = "{\"user_name\":\"bflee\",\"id\":\"123456\"}";
    	 Map<String,String> map = new HashMap<>();
    	 map.put("user_name", "bflee");
    	 map.put("id_number", "123456");
    	 try {
			User s = toSnakeObject(json,User.class);
			System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
