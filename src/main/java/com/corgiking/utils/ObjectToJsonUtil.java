package com.corgiking.utils;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class ObjectToJsonUtil {
	private static Logger log = Logger.getLogger(ObjectToJsonUtil.class);

	public static String ToJson(Object object) {
		Gson gson = new Gson();
		String json = gson.toJson(object);
		log.info(json);
		return json;
	}
}
