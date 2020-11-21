package com.brunnerit.gofastdfs.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brunnerit.gofastdfs.msg.GoFastdfsResult;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json 工具类
 * 
 * @author zhenhua wang
 *
 */
public final class JsonUtils {
	public static final ObjectMapper mapper = new ObjectMapper();

	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	public JsonUtils() {
		// 反序列化的时候如果多了其他属性,不抛出异常
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(Include.NON_NULL);  
	}

	/**
	 * 对象转换为json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toStr(Object obj) {
		if (obj == null) {
			return null;
		}
		if (obj.getClass() == String.class) {
			return (String) obj;
		}
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("json序列化出错：" + obj, e);
			return null;
		}
	}

	public static <T> T toBean(String json, Class<T> tClass) {
		try {
			return mapper.readValue(json, tClass);
		} catch (IOException e) {
			logger.error("json解析出错：" + json, e);
			return null;
		}
	}

	public static <E> List<E> toList(String json, Class<E> eClass) {
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, eClass));
		} catch (IOException e) {
			logger.error("json解析出错：" + json, e);
			return null;
		}
	}

	public static <K, V> Map<K, V> toMap(String json, Class<K> kClass, Class<V> vClass) {
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
		} catch (IOException e) {
			logger.error("json解析出错：" + json, e);
			return null;
		}
	}

	public static <T> T nativeRead(String json, TypeReference<T> type) {
		try {
			return mapper.readValue(json, type);
		} catch (IOException e) {
			logger.error("json解析出错：" + json, e);
			return null;
		}
	}

	public static <T> GoFastdfsResult<T> readGoFastDfsResult(String json, Class<T> tClass) {
		try {
			JavaType javaType = mapper.getTypeFactory().constructParametricType(GoFastdfsResult.class, tClass);
			return mapper.readValue(json, javaType);
		} catch (IOException e) {
			logger.error("json解析出错：" + json, e);
			return null;
		}
	}

	public static <E> GoFastdfsResult<List<E>> readreadGoFastDfsResultList(String json, Class<E> eClass) {
		try {
			JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, eClass);
			JavaType javaType = mapper.getTypeFactory().constructParametricType(GoFastdfsResult.class, listType);
			return mapper.readValue(json, javaType);
		} catch (IOException e) {
			logger.error("json解析出错：" + json, e);
			return null;
		}
	}
}
