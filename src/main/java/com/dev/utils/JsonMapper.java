package com.dev.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class JsonMapper {

    private static Gson gson;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    public static String mapToJson(Object obj) {
        return gson.toJson(obj);
    }

    public static Object mapToObject(String jsonString) {
        Type targetClassType = null;
        try {
            targetClassType = TypeToken.getParameterized(Object.class).getType();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return gson.fromJson(jsonString, targetClassType);
    }

}
