package com.yang.jupiter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yang.jupiter.service.AccountService;

import lombok.AllArgsConstructor;

@RequestMapping("v1")
@RestController
@AllArgsConstructor
public class AccountController {
	
	private final AccountService accountService;
	
	
	@GetMapping("/account")
	public String getAccount() {
		
		
		return accountService.getAccount();
	}

}
