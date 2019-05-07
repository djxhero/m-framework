package com.lancslot.morn.utils.constant;

public enum CommonResultCode {
	SUCCESS(10000, "OK"),
	SYS_ERROR(99999, "服务异常，请稍后再试"),
	CODE_PARAM(10002 , "请求参数错误，具体信息详见msg字段"),
	NO_SESSION(10038, "对不起，当前Session失效,请重新登录"),
	;


	public final int code;
	public final String msg;

	CommonResultCode(int code, String msg) {
		this.msg = msg;
		this.code = code;
	}

}
