package com.backend.xmart.dto.response;

import lombok.Data;

@Data
public class ResponseBody {
	private long total;
	private Object data;
	private String message;
	private int statusCode;
	private String status;
}
