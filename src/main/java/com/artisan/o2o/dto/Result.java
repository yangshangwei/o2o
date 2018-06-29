package com.artisan.o2o.dto;

/**
 * 
 * 
 * @ClassName: Result
 * 
 * @Description: 封装json对象，所有返回结果都使用它
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年6月16日 上午1:55:55
 * 
 * @param <T>
 */
public class Result<T> {
	
	// 是否成功的标识
	private boolean success;
	
	// 成功时返回的数据
	private T data;

	// 错误码
	private int errorCode;

	// 错误信息
	private String errMsg;

	/**
	 * 
	 * @Title:Result
	 * 
	 * @Description:空的构造函数
	 */
	public Result() {
		super();
	}

	/**
	 * 
	 * @Title:Result
	 * 
	 * @Description:数据获取成功时使用的构造器
	 * 
	 * @param success
	 * @param data
	 */
	public Result(boolean success, T data) {
		this.success = success;
		this.data = data;
	}
	
	/***
	 * 
	 * 
	 * @Title:Result
	 * 
	 * @Description:数据获取失败时使用的构造器
	 * 
	 * @param success
	 * @param errorCode
	 * @param errMsg
	 */
	public Result(boolean success, int errorCode, String errMsg) {
		this.success = success;
		this.errorCode = errorCode;
		this.errMsg = errMsg;
		
	}
	
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}


}
