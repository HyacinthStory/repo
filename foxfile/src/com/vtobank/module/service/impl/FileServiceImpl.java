package com.vtobank.module.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vtobank.module.dao.FileRepository;
import com.vtobank.module.domain.BaseService;
import com.vtobank.module.entity.File;
import com.vtobank.module.service.FileService;

/**
 * 文件处理服务实现
 * @author zhangfb
 * @version 1.0.0.1
 */
@Service
@Transactional(readOnly = true)
public class FileServiceImpl extends BaseService implements FileService {
	
	@Autowired
	private FileRepository repository;

	/**
	 * @param f
	 * @return 
	 * @see com.vtobank.module.service.FileService#save(com.vtobank.module.entity.File) 
	 */ 
	@Override
	@Transactional(readOnly = false)
	public File save(File f) {
		if (f == null) {
			return null;
		}
		return repository.save(f);
	}

	/**
	 * @param id
	 * @return 
	 * @see com.vtobank.module.service.FileService#get(java.lang.Long) 
	 */ 
	@Override
	public File get(Long id) {
		return repository.getById(id);
	}

	/**
	 * @param f
	 * @return 
	 * @see com.vtobank.module.service.FileService#update(com.vtobank.module.entity.File) 
	 */ 
	@Override
	@Transactional(readOnly = false)	
	public File update(File f) {
		return repository.update(f);
	}
}
