package com.xdomain.user.security.filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractSimpledGrantedAuthorities {

	//Necesario para cuando se creen los objetos autorities ya que lo necesitamos para poder pasarle los roles como json.
	
	@JsonCreator
	public AbstractSimpledGrantedAuthorities(@JsonProperty ("authority") String role) {
		
	}
	
}
