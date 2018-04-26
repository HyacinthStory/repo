/********************************************
 * 功能说明: 持久层基础接口
 * 模块名称: 公共模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2017年11月12日 上午1:38:18
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.vtobank.system.entity.PageCond;

/**
 * 持久层基础接口
 * 
 * @author zhangfb
 * @version 1.0.0.1
 */
public interface BaseRepository<T extends Serializable, ID extends Serializable> {

	public T save(T t);

	public T delete(T t);
	
	/**
	 * 获得对象时加锁
	 */
	public T getWithLock(ID id);
	/**
	 * 根据查询语句获取对象(取第一个)
	 * @param hql
	 * @return
	 */
	public T get(String hql);
	/**
	 * 根据对象的unique字段获取对象
	 */
	public T getUniqueResult(String attrName, Serializable attrValue);
	/**
	 * 根据对象的unique字段获取对象
	 */
	public T getUniqueResult(String hql, Map<String, Object> paramMap);

	public T update(T t);

	public List<T> selectByExample(T t, int... pages);
	
	public int queryUpdate(String hql);

	public int querySQLUpdate(String sql);

	/**
     * 根据id删除数据
     * @param id
     */
    void deleteById(ID id);

    /**
     * 通过id获取数据
     * @param id
     * @return
     */
    T getById(ID id);

    /**
     * 有条件查询多条数据
     * @param hql 	查询语句
     * @param startIndex 	起始下标
     * @param dataNum 	数据数目
     * @return
     */
    List<T> query(String hql, Integer startIndex, Integer dataNum);

    /**
     * 根据hql语句获取对象集合
     * @param hql           hql语句
     * @param paramMap      参数map
     * @param page          分页对象
     */
    List<T> queryWithHqlByPage(String hql, Map<String, Object> paramMap, PageCond page);

    /**
     * 根据sql语句获取Map列表
     * @param sql
     * @return
     */
    List<Map<String, Object>> queryMapWithSQLAndPage(String sql,PageCond page);

    /**
     * 样例查询
     * @param t
     * @param page
     * @return
     */
    public List<T> selectByExample(T t,PageCond page);

    /**
     * 样例查询 统计总条数
     * @param t
     * @return
     */
    public int selectByExampleRowsNum(T t);
    /**
     * 将对象从session的缓存中清除
     */
    boolean evict(T t);

}
