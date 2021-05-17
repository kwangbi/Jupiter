package com.yang.jupiter.dto;

import javax.ws.rs.FormParam;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TestDTO {

	@FormParam("name")
	@JsonProperty("name")
	private String name;
	
	@FormParam("email")
	@JsonProperty("email")
	private String email;

}
