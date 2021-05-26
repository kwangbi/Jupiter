package com.yang.jupiter.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yang.jupiter.dto.ListTableDTO;


@Mapper
public interface ListTableMapper {
	List<ListTableDTO> selectListTable();
	

}
