package com.yang.jupiter.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.yang.jupiter.api.entity.AccountDto;

public interface AccountRepository extends CrudRepository<AccountDto, String> {

}
