package com.nagesh.ordermanagement.Util;

import net.sf.json.JSONObject;

public class CommonResponse {

	public static JSONObject getSuccessResponse(String status, String statusMsg) {
		JSONObject respObject = new JSONObject();
		respObject.put("status", status);
		respObject.put("message", statusMsg);
		return respObject;
	}

}
