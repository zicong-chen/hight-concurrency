package top.chenzicong.highconcurrency.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.chenzicong.highconcurrency.exception.MyException;

import java.io.IOException;
import java.util.List;

public class JsonUtil {
    public static ObjectMapper mapper = new ObjectMapper();

    public static String ObjToString(Object o) {
        String s;
        if (o == null) {
            return null;
        }
        if (o instanceof String) {
            return (String) o;
        }
        try {
            s = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new MyException("Obj to String exception：" + e.getMessage());
        }
        return s;
    }

    public static <T> T StringToObj(String str, Class<T> clazz) {
        T obj;
        if (str == null) {
            return null;
        }
        try {
            obj = mapper.readValue(str, clazz);
        } catch (IOException e) {
            throw new MyException("String to Obj exception：" + e.getMessage());
        }
        return obj;
    }

    public static <T> List<T> StringToListObj(String strs, TypeReference<List<T>> typeReference) {
        List<T> list;
        if (strs == null) {
            return null;
        }
        try {
            list = mapper.readValue(strs, typeReference);
        } catch (IOException e) {
            throw new MyException("String to ListObj exception：" + e.getMessage());
        }
        return list;
    }
}
