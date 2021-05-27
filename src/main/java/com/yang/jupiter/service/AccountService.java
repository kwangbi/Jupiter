package com.yang.jupiter.service;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.yang.jupiter.core.constants.CommonConstants;
import com.yang.jupiter.dto.ListTableDTO;
import com.yang.jupiter.entity.AccountDto;
import com.yang.jupiter.mapper.ListTableMapper;
import com.yang.jupiter.repository.AccountRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final ListTableMapper mapper;
	
	public String getAccount() {
				
		AccountDto dto = new AccountDto();
		dto.setId("kwangbi");
		dto.setUsername("양광모");
		dto.setMail("kwangbi@naver.com");
		accountRepository.save(dto);
		
		Optional<AccountDto> id = accountRepository.findById(dto.getId());
		
		log.info("name : {}",id.get().getUsername());
		log.info("mail : {}",id.get().getMail());
		
		
		return "ok";
	}
	
	public String getCacheTestService(String param) {
		
		log.debug("here");
		
		boolean isOkCode = true;
		
		//public static final String[] OK_CODE = {"00","PCW_DTS_E0301","PCW_DTS_E0302"};
		isOkCode = Arrays.stream(CommonConstants.OK_CODE).anyMatch(param::equals);
		
		log.debug("isOkCode : {}",isOkCode);
		
		
		return "ok";
	}
	
	public List<ListTableDTO> getListTable(){
		
		List<ListTableDTO> list = mapper.selectListTable();
		
		return list;
	}

}
