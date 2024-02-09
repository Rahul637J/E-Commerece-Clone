package com.clone.ecommerece.util;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;


@Component
@NoArgsConstructor
public class ResponseStructure<T> {
	private int status;
	private String msg;
	private T data;
	
	public int getStatus() {
		return status;
	}
	public ResponseStructure<T> setStatus(int status) {
		this.status = status;
		return this;
		
	}
	public String getMsg() {
		return msg;
	}
	public ResponseStructure<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public T getData() {
		return data;
	}
	public ResponseStructure<T> setData(T data) {
		this.data = data;
		return this;
	}
}
