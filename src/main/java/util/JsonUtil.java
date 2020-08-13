package util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mysql.cj.util.StringUtils;
import util.log.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;


public class JsonUtil {

    static Log log = Log.threadLog();

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        // 如果为空则不输出
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 对于空的对象转json的时候不抛出错误
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 禁用序列化日期为timestamps
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 禁用遇到未知属性抛出异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 视空字符传为null
//        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 低层级配置
//        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 允许属性名称没有引号
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号
//        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 取消对非ASCII字符的转码
//        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);

    }

    public static ObjectMapper getDefaultMapper() {
        return mapper;
    }

    public static <T> String toJson(T t) {
        return toJson(t, mapper);
    }

    public static <T> T jsonTo(String json, TypeReference<T> typeReference) {
        return jsonTo(json, typeReference, null);
    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        return jsonToBean(json, clazz, mapper);
    }

    /**
     * 将json 转成 List
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = getCollectionType(mapper, ArrayList.class, clazz);
        List<T> list = null;
        try {
            list  =  mapper.readValue(json, javaType);   //这里不需要强制转换
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T> List<T> jsonToList(String json, Class<T> clazz, Boolean userDefault) {
        if (!userDefault) {
            return jsonToList(json, clazz);
        }
        JavaType javaType = getCollectionType(mapper, ArrayList.class, clazz);
        List<T> list = null;
        try {
            list  =  mapper.readValue(json, javaType);   //这里不需要强制转换
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static <T> T fileToBean(File file, Class<T> clazz) {
        return fileToBean(file, clazz, null);
    }

    public static <T> String toJson(T t, ObjectMapper objectMapper) {
        if (Objects.isNull(t))
            return null;
        try {
            return objectMapper == null ? mapper.writeValueAsString(t) : objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            log.error("to json error:" + t, e);
            return null;
        }
    }

    public static <T> T jsonToBean(String json, Class<T> clazz, ObjectMapper objectMapper) {
        if (StringUtils.isNullOrEmpty(json))
            return null;
        try {
            return objectMapper == null ? mapper.readValue(json, clazz) : objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("json to bean error:" + json, e);
            return null;
        }
    }

    public static <T> T fileToBean(File file, Class<T> clazz, ObjectMapper objectMapper) {
        if (!file.exists())
            return null;
        try {
            return objectMapper == null ? mapper.readValue(file, clazz) : objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            log.error("filter to bean error:" + file.getName(), e);
            return null;
        }
    }

    public static <T> T jsonTo(String json, TypeReference<T> typeReference, ObjectMapper objectMapper) {
        if (StringUtils.isNullOrEmpty(json))
            return null;
        try {
            return objectMapper == null ? mapper.readValue(json, typeReference) : objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            log.error("json to map error:" + json, e);
            return null;
        }
    }

    public static <T> T jsonTo(String json, Class<T> clazz, ObjectMapper objectMapper) {
        if (StringUtils.isNullOrEmpty(json))
            return null;
        try {
            return mapper.readValue(json, new TypeReference<T>(){});
        } catch (Exception e) {
            log.error("json to map error:" + json, e);
            return null;
        }
    }

    /**
     * 将Map 转成Clazz
     * @param object
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Object object, Class<T> clazz) {
        return mapToBean(object, clazz, null);
    }

    public static <T> T mapToBean(Object object, Class<T> clazz, ObjectMapper objectMapper) {
        if (object != null && object instanceof LinkedHashMap) {
            String json = toJson(object);
            return jsonToBean(json, clazz);
        }else {
            log.error("不是map");
            return null;
        }

    }

    public static  <T> List<T> listToBean(Object value, Class<T> clazz) {
        if (value != null && value instanceof List ) {
            String json = toJson(value);
            return jsonToList(json, clazz);
        }else {
            log.error("错误转换");
            return null;
        }
    }
}