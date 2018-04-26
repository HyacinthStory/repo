package com.vtobank.module.dao.impl;

import org.springframework.stereotype.Repository;

import com.vtobank.module.dao.ServerConfigRepository;
import com.vtobank.module.domain.BaseRepositoryImpl;
import com.vtobank.module.entity.ServerConfig;

@Repository
public class ServerConfigRepositoryImpl extends BaseRepositoryImpl<ServerConfig, Long> implements ServerConfigRepository{

	/**
	 * 获取服务器配置
	 * @param useType
	 * @return 
	 * @see com.vtobank.module.dao.ServerConfigRepository#getByUseType(java.lang.String) 
	 */ 
	@Override
	public ServerConfig getByUseType(String useType) {
		return null;
	}
	
}
