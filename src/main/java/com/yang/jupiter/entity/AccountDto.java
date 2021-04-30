package com.yang.jupiter.entity;

import javax.persistence.Id;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@RedisHash("account")
@Data
public class AccountDto {
	
	@Id
	private String id;
	private String username;
	private String mail;

}
