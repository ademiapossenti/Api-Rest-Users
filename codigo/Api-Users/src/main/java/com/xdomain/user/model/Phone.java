package com.xdomain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Phone {
	
	private int id;
	private String number;
	private String citycode;
	private String countryCode;
	
}
