package com.yang.jupiter.repository;

import org.springframework.data.repository.CrudRepository;

import com.yang.jupiter.entity.AccountDto;

public interface AccountRepository extends CrudRepository<AccountDto, String> {

}
