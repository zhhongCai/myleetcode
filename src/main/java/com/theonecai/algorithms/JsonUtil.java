package com.theonecai.algorithms;

import org.apache.htrace.fasterxml.jackson.annotation.JsonInclude;
import org.apache.htrace.fasterxml.jackson.databind.MapperFeature;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @Author: theonecai
 * @Date: Create in 2019-07-08 14:45
 * @Description:
 */
public class JsonUtil {
    /**
     * 序列化非空字段，按字段名称排序
     */
    private static ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);

    public static String toString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            return null;
        }
    }
}
