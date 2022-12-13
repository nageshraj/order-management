package com.nagesh.ordermanagement.Util;

import com.google.gson.Gson;

public class JsonUtil {

	static Gson gson = new Gson();

	public static String toJSON(Object obj) {
		return gson.toJson(obj);
	}

	public static Object fromJson(String jsonStr, Class<? extends Object> class1) throws Exception {

		Object object = null;
		try {
			object = gson.fromJson(jsonStr, class1);
		} catch (Exception e) {
			throw new Exception("Invalid Json");
		}
		return object;
	}

}
