package com.itheima.dao;

import java.util.List;

import com.itheima.domain.BaseDict;

public interface BaseDictDao {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
