package com.vtobank.module.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtobank.module.dao.ServerConfigRepository;
import com.vtobank.module.domain.BaseService;
import com.vtobank.module.entity.ServerConfig;
import com.vtobank.module.service.ServerConfigService;
import com.vtobank.util.StringUtils;

/**
 * 服务器配置服务类
 * @author zhangfb
 * @version 1.0.0.1
 */
@Service
@Transactional(readOnly = true)
public class ServerConfigServiceImpl extends BaseService implements ServerConfigService {

	@Autowired
	private ServerConfigRepository repository;
	
	/**
	 * 获取服务器配置
	 * @param serverUseType
	 * @return ServerConfig
	 * @see com.vtobank.module.service.ServerConfigService#getByUseType(java.lang.String) 
	 */ 
	@Override
	public ServerConfig getByUseType(String serverUseType) {
		if(StringUtils.isBlank(serverUseType)){			
			return null;
		}
		return repository.getUniqueResult("useType", serverUseType);
	}
	
}
