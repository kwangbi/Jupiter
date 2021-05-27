package com.yang.jupiter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yang.jupiter.dto.ListTableDTO;
import com.yang.jupiter.dto.TestDTO;
import com.yang.jupiter.service.AccountService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@RequestMapping("v1")
@RestController
@AllArgsConstructor
public class AccountController {
	
	private final AccountService accountService;
	
	
	@GetMapping("/account")
	public String getAccount() {
		
		
		return accountService.getAccount();
	}
	
	@PostMapping("/postTest")
	public String getPostTest(
			@Parameter(description = "파라메터")
			@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "1111111111") 
			@RequestBody(required = true) @Valid TestDTO param
			) {
		
		log.debug("param : {}", param.toString());
		
		
		return "ok";
	}
	
	
	@PostMapping("/postTest2")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML }) 
	@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,content = @Content(mediaType = MediaType.APPLICATION_JSON,schema = @Schema(implementation = TestDTO.class)))
	public String getPostTest2(@BeanParam TestDTO param) {
		log.debug("param : {}", param.toString());
		
		return "ok";
	}

	@Cacheable(value = "post-single",key = "#id",unless = "#result == null")
	@GetMapping("/cacheTest/{id}")
	public String getCacheTest(@PathVariable String id) {
		log.debug("id {}",id);
		
		return accountService.getCacheTestService();
	}
	
	@Cacheable(value = "getListTableData",key = "1" ,unless = "#result == null")
	@GetMapping("/cacheList")
	public List<ListTableDTO> getListTableData(){
		
		log.debug("1111111");
		
		return accountService.getListTable();
	}
	
	@Cacheable(value = "getListTableData", keyGenerator = "customKeyGenerator" ,unless = "#result == null")
	@GetMapping("/cacheList2")
	public List<ListTableDTO> getListTableData2(){
		
		log.debug("1111111");
		
		return accountService.getListTable();
	}	
}
