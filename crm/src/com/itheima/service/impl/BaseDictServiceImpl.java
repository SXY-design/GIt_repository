package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.BaseDictDao;
import com.itheima.domain.BaseDict;
import com.itheima.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService {
	private BaseDictDao baseDictDao;

	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}
	
}
