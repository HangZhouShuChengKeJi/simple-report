package com.orange.commons.web.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class JsonUtils {

	public static String getJsonString(Object o) {
		Gson gson = new Gson();
		return gson.toJson(o);
	}

	public static <T> T toBean(String json, Type t) {
		Gson gson = new Gson();
		return gson.fromJson(json, t);
	}

	private static Gson getJson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyyMMddHHmmssSSS").create();
		return gson;
	}
}
