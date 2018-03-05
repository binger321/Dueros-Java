package com.car.ctl.demo.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/***
 * 简单的json转换工具类
 *
 */
public final class MyEasyJsonUtil {
	
	/***
	 * 字符串转java对象, 非list类型
	 * @param json
	 * @param clazz 简单的pojo的Class
	 * @return
	 */
	public static <T> T string2json(String json, Class<T> clazz){
		ObjectMapper mapper = new ObjectMapper();
		try{
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(json, clazz);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/***
	 * 字符串转list类型
	 * @param json
	 * @param beanClazz 简单的pojo的Class
	 * @return
	 */
	public static <T> List<T> string2jsonList(String json, Class<T> beanClazz){
		ObjectMapper mapper = new ObjectMapper();
		try{
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanClazz);
			return mapper.readValue(json, javaType);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Java对象转字符串
	 * @param plainPojo
	 * @return json字符串
	 */
	@org.jetbrains.annotations.Nullable
	public static String json2string(Object plainPojo){
		ObjectMapper mapper = new ObjectMapper();
		try{
			return mapper.writeValueAsString(plainPojo);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
