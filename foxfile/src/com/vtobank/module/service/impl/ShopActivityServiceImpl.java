package com.vtobank.module.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vtobank.module.dao.ShopActivityRepository;
import com.vtobank.module.domain.BaseService;
import com.vtobank.module.service.ShopActivityService;

/**
 * 商户促销服务实现
 * @author zhangfb
 * @version 1.0.0.1
 */
@Service
@Transactional(readOnly = true)
public class ShopActivityServiceImpl extends BaseService implements ShopActivityService {
	
	@Autowired
	private ShopActivityRepository shopActivityRepository;

	
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public int getShopActivityAndUpdate(String type,String hsql) throws Exception {
		int res = 0; 
		if(type.equals("01")){ // hql
			res = shopActivityRepository.queryUpdate(hsql);
		} else {  //sql
			res = shopActivityRepository.querySQLUpdate(hsql);
		}
		return res;
	}
	
	
}
