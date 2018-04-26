/********************************************
 * 功能说明: 服务支持类
 * 模块名称: 服务模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhaorq
 * 开发时间: 2017年5月4日 上午11:01:14
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.domain;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务支持类
 * @author zhaorq
 * @verion 1.0.0.1
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity> extends BaseService {

	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(Long id) {
		if (id == null) {
			return null;
		}
		return dao.get(id);
	}
	
	/**
	 * 根据编码获取单条数据
	 * @param id
	 * @return
	 */
	public T getByCode(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}
		return dao.getByCode(code);
	}
	
	/**
	 * 根据实体获取单条数据
	 * @param entity
	 * @return
	 */
	public T getByEntity(T entity) {
		return dao.getByEntity(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> query(T entity) {
		return dao.query(entity);
	}
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<T> queryAll() {
		return dao.queryAll();
	}
	
	
	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public boolean save(T t) {
		int iRet = 0;
		if (t.getId() == null){
			iRet = dao.insert(t);
		}else{
			iRet = dao.update(t);
		}
		return iRet == 1;
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public int delete(T entity) {
		if (entity.getId() == null && StringUtils.isBlank(entity.getCode())) {
			return 0;
		}
		return dao.delete(entity);
	}
	
}
