package com.mmall.common;

import java.io.Serializable;


import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
//忽略value为空的key
public class ServerResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5359047594277598720L;
	private int status;
	private String msg;
	private T data;

	/**
	 * @param status
	 */
	private ServerResponse(int status) {
		this.status = status;
	}

	/**
	 * @param status
	 * @param data
	 */
	private ServerResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}

	/**
	 * @param status
	 * @param msg
	 */
	private ServerResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	/**
	 * @param status
	 * @param msg
	 * @param data
	 */
	private ServerResponse(int status, String msg, T data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public int getStatus() {
		return this.status;
	}
	public T getData() {
		return this.data;
	}
	public String getMsg() {
		return this.msg;
	}
	
	@JsonIgnore
	//使之不出现在json结果
	public boolean isSuccess() {
		return this.status==ResponseCode.SUCCESS.getCode();
	}
	
	public static <T> ServerResponse<T> createBySuccess(){
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
	}
	
	public static <T> ServerResponse<T> createBySuccessMessage(String msg){
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
	}
	
	public static <T> ServerResponse<T> createBySuccess(T data){
		return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
	}
	
	public static <T> ServerResponse<T> createBySuccess(String msg,T data){
		return new ServerResponse<>(ResponseCode.SUCCESS.getCode(), msg,data);
	}
	
	public static <T> ServerResponse<T> createByError(){
		return new ServerResponse<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
	}
	
	public static <T> ServerResponse<T> createByErrorMessage(String errorMsg){
		return new ServerResponse<>(ResponseCode.ERROR.getCode(), errorMsg);
	}
	
	public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMsg){
		return new ServerResponse<>(errorCode,errorMsg);
	}
	
}
