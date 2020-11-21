package com.brunnerit.gofastdfs.msg;

import java.io.Serializable;

/**
 * GoFastDfs统一返回报文类似
 * 
 * @author zhenhua wang
 *
 */
public class GoFastdfsResult<T> implements Serializable{
	private static final long serialVersionUID = 995644672235276579L;
	private static final String succ = "ok";
	private T data;
	private String message;
	private String status;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSucc() {
		return status != null && status.equals(succ);
	}
}
