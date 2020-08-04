package com.xdomain.user.model;

import com.xdomain.user.utils.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseUserStatus {

	private String code;
	private String description;
	
	public ResponseUserStatus(int code, String description) {
		this.code = Constants.USER_PREFIX_ERROR + "-" + code;
		this.description = description;
	}
	
}
