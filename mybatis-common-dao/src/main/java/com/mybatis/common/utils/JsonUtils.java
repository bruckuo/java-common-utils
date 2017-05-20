package com.mybatis.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ description:
 * @ author: guojiang.xiong
 * @ created: 2017-05-19 下午5:37
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public JsonUtils() {
    }

    public static ObjectMapper getObjectMapper() {
        mapper.setPropertyNamingStrategy((PropertyNamingStrategy) null);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    public static ObjectMapper getObjectMapper(PropertyNamingStrategy strategy) {
        return mapper.setPropertyNamingStrategy(strategy);
    }

    public static <T> List<T> toObject(String jsonStr, TypeReference<List<T>> reference) {
        ObjectMapper mapper = getObjectMapper();

        try {
            return (List) mapper.readValue(jsonStr, reference);
        } catch (IOException var4) {
            logger.error("resolve object error, {}, {}", jsonStr, var4);
            return null;
        }
    }

    public static <T> List<T> toObject(ObjectMapper mapper, String jsonStr, TypeReference<List<T>> reference) throws IOException {
        return (List) mapper.readValue(jsonStr, reference);
    }

    public static <K, V> Map<K, V> toMap(ObjectMapper mapper, String jsonStr, TypeReference<Map<K, V>> reference) throws IOException {
        return (Map) mapper.readValue(jsonStr, reference);
    }

    public static <T> T toObject(String jsonStr, Class<T> valueType) {
        ObjectMapper mapper = getObjectMapper();

        try {
            return mapper.readValue(jsonStr, valueType);
        } catch (IOException var4) {
            logger.error("resolve object error, {}, {}", jsonStr, var4);
            return null;
        }
    }

    public static <T> T toObject(ObjectMapper mapper, String jsonStr, Class<T> valueType) {
        try {
            return mapper.readValue(jsonStr, valueType);
        } catch (IOException var4) {
            logger.error("resolve object error, {}, {}", jsonStr, var4);
            return null;
        }
    }

    public static JsonNode getNode(String jsonStr, String nodeName) throws IOException {
        ObjectMapper mapper = getObjectMapper();
        JsonNode jsonTree = mapper.readTree(jsonStr);
        return jsonTree.get(nodeName);
    }

    public static JsonNode getNode(ObjectMapper mapper, String jsonStr, String nodeName) throws IOException {
        JsonNode jsonTree = mapper.readTree(jsonStr);
        return jsonTree.get(nodeName);
    }

    public static <T> T toObject(String jsonStr, Class<T> valueType, String nodeName) throws IOException {
        JsonNode eventsNode = getNode(jsonStr, nodeName);
        return toObject(eventsNode.toString(), valueType);
    }

    public static <T> T toObject(ObjectMapper mapper, String jsonStr, Class<T> valueType, String nodeName) throws IOException {
        JsonNode eventsNode = getNode(mapper, jsonStr, nodeName);
        return toObject(mapper, eventsNode.toString(), valueType);
    }

    public static <T> List<T> toObject(String jsonStr, TypeReference<List<T>> reference, String nodeName) throws IOException {
        JsonNode eventsNode = getNode(jsonStr, nodeName);
        return toObject(mapper, eventsNode.toString(), reference);
    }

    public static <T> List<T> toObject(ObjectMapper mapper, String jsonStr, TypeReference<List<T>> reference, String nodeName) throws IOException {
        JsonNode eventsNode = getNode(mapper, jsonStr, nodeName);
        return toObject(mapper, eventsNode.toString(), reference);
    }

    public static String toString(Object obj) {
        ObjectMapper mapper = getObjectMapper();

        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException var3) {
            logger.error("resolve object error, {}, {}", obj, var3);
            return null;
        }
    }

    public static String toString(ObjectMapper mapper, Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException var3) {
            logger.error("resolve object error, {}, {}", obj, var3);
            return null;
        }
    }
}

