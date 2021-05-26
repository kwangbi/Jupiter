package com.yang.jupiter.dto;

import javax.persistence.Id;

import lombok.Data;

@Data
public class ListTableDTO {
	
	@Id
	private int idx;
	private String value1;
	private String value2;
}
