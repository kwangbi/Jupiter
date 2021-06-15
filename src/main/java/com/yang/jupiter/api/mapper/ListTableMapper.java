package com.yang.jupiter.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yang.jupiter.api.dto.ListTableDTO;


@Mapper
public interface ListTableMapper {
	List<ListTableDTO> selectListTable();
	

}
