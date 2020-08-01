package com.xdomain.user.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "phones")
public class PhoneEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String number;
	
	@Column(name="city_code")
	private String citycode;
	
	@Column(name="country_code")
	private String countryCode;
	
	public PhoneEntity(	String number,String citycode, String countryCode) {
		this.number = number;
		this.citycode = citycode;
		this.countryCode = countryCode;

	}



	
	
}
