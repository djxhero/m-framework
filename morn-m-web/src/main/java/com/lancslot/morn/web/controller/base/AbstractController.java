package com.lancslot.morn.web.controller.base;


import com.lancslot.morn.constant.CommonResult;
import com.lancslot.morn.constant.CommonResultCode;

/**
 * 
 * 公共类。统一消息返回格式。
 *
 */
public  class AbstractController {
	
	public static CommonResult<Object> buildSuccess() {
		return CommonResult.build(CommonResultCode.SUCCESS.code, CommonResultCode.SUCCESS.msg, new Object());
	}
	public static <T> CommonResult<T> buildSuccess(T data) {
		return CommonResult.build(CommonResultCode.SUCCESS.code, CommonResultCode.SUCCESS.msg, data);
	}

	public static CommonResult<Object> buildFailed() {
		return CommonResult.build(CommonResultCode.SYS_ERROR.code, CommonResultCode.SYS_ERROR.msg, new Object());
	}
	
	public static <T> CommonResult<T> buildFailed(T data) {
		return CommonResult.build(CommonResultCode.SYS_ERROR.code, CommonResultCode.SYS_ERROR.msg, data);
	}
	
	public static CommonResult<Object> buildFailed(CommonResultCode errCode) {
		return CommonResult.build(errCode.code, errCode.msg, new Object());
	}
	
	public static <T> CommonResult<T> buildFailed(CommonResultCode errCode, T data) {
		return CommonResult.build(errCode.code, errCode.msg, data);
	}
	
	public static CommonResult<Object> buildFailed(CommonResultCode errCode, String errMsg) {
		return CommonResult.build(errCode.code, errMsg, new Object());
	}

	public static <T> CommonResult<T> build(int code, String msg, T data) {
		return CommonResult.build(code, msg, data);
	}
}
