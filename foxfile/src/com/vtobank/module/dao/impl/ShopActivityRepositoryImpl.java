package com.vtobank.module.dao.impl;

import org.springframework.stereotype.Repository;

import com.vtobank.module.dao.ShopActivityRepository;
import com.vtobank.module.domain.BaseRepositoryImpl;
import com.vtobank.module.entity.ShopActivity;

@Repository
public class ShopActivityRepositoryImpl extends BaseRepositoryImpl<ShopActivity, Long> implements ShopActivityRepository{
	
}
