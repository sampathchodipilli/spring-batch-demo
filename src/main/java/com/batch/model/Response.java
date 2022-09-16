package com.batch.model;

public class Response {
	private String code;
	private String status;
	private String message;

	public Response() {
		super();
	}

	public Response(String code, String status, String message) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", status=" + status + ", message=" + message + "]";
	}

}
