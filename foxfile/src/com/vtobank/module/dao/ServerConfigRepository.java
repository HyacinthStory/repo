package com.vtobank.module.dao;

import com.vtobank.module.domain.BaseRepository;
import com.vtobank.module.entity.ServerConfig;

public interface ServerConfigRepository extends BaseRepository<ServerConfig, Long> {

	/**
	 * 获取服务器配置
	 * @param useType
	 * @return
	 */
	ServerConfig getByUseType(String useType);
}
