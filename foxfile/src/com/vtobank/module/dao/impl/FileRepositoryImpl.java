package com.vtobank.module.dao.impl;

import org.springframework.stereotype.Repository;

import com.vtobank.module.dao.FileRepository;
import com.vtobank.module.domain.BaseRepositoryImpl;
import com.vtobank.module.entity.File;

@Repository
public class FileRepositoryImpl extends BaseRepositoryImpl<File, Long> implements FileRepository{
	
}
