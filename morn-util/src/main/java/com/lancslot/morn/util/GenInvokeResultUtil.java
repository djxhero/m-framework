package com.lancslot.morn.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lancslot.morn.constant.CommonResultCode;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.Map;

public class GenInvokeResultUtil {

    public static JSONObject genSuccessResponse() {
        return genSuccessResponse(null , null);
    }

    public static JSONObject genSuccessResponse(String message) {
        return genSuccessResponse(message , null);
    }


    public static JSONObject genFailureResponse(CommonResultCode resultCode) {
        return genFailureResponse("" , resultCode);
    }

    public static JSONObject genFailureResponse(String message) {
        return genFailureResponse(message , CommonResultCode.SYS_ERROR);
    }

    public static JSONObject genFailureResponse(String message , CommonResultCode resultCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code" , resultCode.code);
        jsonObject.put("msg" , StringUtils.hasText(message) ? message : resultCode.msg);
        jsonObject.put("data" , new JSONObject());
        return jsonObject;
    }

    public static JSONObject genFailureResponse(String message, CommonResultCode resultCode, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", resultCode.code);
        jsonObject.put("msg", StringUtils.hasText(message) ? message : resultCode.msg);
        jsonObject.put("data", data);
        return jsonObject;
    }

	public static JSONObject genFailureResponse(CommonResultCode resultCode, Object data) {
		return genFailureResponse(null, resultCode, data);
	}

    public static JSONObject genSuccessResponse(JSONObject data) {
        return genSuccessResponse(null , data);
    }

    public static JSONObject genSuccessResponse(String message , JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code" , CommonResultCode.SUCCESS.code);
        jsonObject.put("msg" , StringUtils.hasText(message) ? message : CommonResultCode.SUCCESS.msg);
        jsonObject.put("data" , data == null ? new JSONObject() : data);
        return jsonObject;
    }

    public static JSONObject genSuccessResponse(Map<String, Object> resultMap) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", CommonResultCode.SUCCESS.code);
        jsonObject.put("msg", CommonResultCode.SUCCESS.msg);
        jsonObject.put("data", resultMap);
        return jsonObject;
    }

    public static final String defaultDateFormat = "yyyy-MM-dd HH:mm:ss";


    public static JSONObject toFailureResponseContent(Errors errors) {

        JSONObject jsonObject = new JSONObject();
        ObjectError error = errors.getAllErrors().get(0);
        jsonObject.put("code" , CommonResultCode.CODE_PARAM.code);
        jsonObject.put("msg" , error.getDefaultMessage());
        jsonObject.put("data" , new JSONObject());
        return jsonObject;
    }


    public String toResponseContent(int resultCode , String message , Object data , String dateFormat) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultCode" , resultCode);
        jsonObject.put("message" , message);
        jsonObject.put("data" , data == null ? new JSONObject() : data);
        return JSONObject.toJSONStringWithDateFormat(jsonObject , dateFormat , SerializerFeature.WriteMapNullValue);
    }

    public static Object toFailureResponseContent(ValidationResult validationResult) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result" , CommonResultCode.CODE_PARAM.code);
        jsonObject.put("msg" , validationResult.getErrorMsg().get(0));
        jsonObject.put("data" , new JSONObject());
        return jsonObject;
    }
}
