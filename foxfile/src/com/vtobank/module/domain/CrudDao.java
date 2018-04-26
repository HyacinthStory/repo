/********************************************
 * 功能说明: DDAO支持接口
 * 模块名称: 持久层模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉科技技术有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhaorq
 * 开发时间: 2016年12月23日 下午9:15:41
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.domain;

import java.util.List;

import com.vtobank.system.entity.PageCond;

/**
 * DAO支持接口
 * @author zhangfb
 * @version 1.0.0.1
 */
public interface CrudDao<T> {
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(Long id);
	
	/**
	 * 根据编码获取单条数据
	 * @param code
	 * @return
	 */
	public T getByCode(String code);
	
	/**
	 * 根据实体获取单条数据
	 * @param entity
	 * @return
	 */
	public T getByEntity(T entity);
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> query(T entity);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<T> queryAll();
	
	/**
	 * 查询分页数据
	 * @param entity
	 * @return
	 */
	public List<T> queryPage(T entity,PageCond page);
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 删除数据
	 * @param entity
	 * @return
	 */
	public int delete(T entity);

}
