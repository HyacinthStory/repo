/**
 * @author   zhangyp
 * @created  2013-10-8 下午8:35:24
 * @email    zviolet@163.com
 * TODO
 */
package com.vtobank.database;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.vtobank.system.entity.PageCond;

public interface IDataSet {
	
	/**
	 * session控制
	 * @return
	 */
	public boolean beginSession();
	public boolean closeSession();
	public boolean commitAndCloseSession();
	public boolean rollbackAndCloseSession();
	public Session getSession();
	
	/**
	 * 根据主键获取对象
	 * @param clazz
	 * @param pkValue
	 * @return
	 */
	public <T> T get(Class<T> clazz, Serializable pkValue);
	/**
	 * 获得对象时加锁
	 */
	public <T> T getWithLock(Class<T> clazz, Serializable pkValue);
	/**
	 * 根据查询语句获取对象(取第一个)
	 * @param hql
	 * @return
	 */
	public <T> T get( String hql );
	/**
	 * 根据对象的unique字段获取对象
	 */
	public <T> T getUniqueResult(Class<T> clazz, String attrName, Serializable attrValue );
	/**
	 * 根据对象的unique字段获取对象
	 */
	public <T> T getUniqueResult(String hql, Map<String, Object> paramMap );
	/**
	 * 根据查询语句获取对象列表
	 * @param clazz
	 * @return
	 */
	public <T> List<T> query( Class<T> clazz );
	
	/**
	 * 根据查询语句获取对象列表
	 * @param clazz
	 * @return
	 */
	public <T> List<T> query( String hql );
	public <T> List<T> query( String hql ,int maxCount );
	public <T> List<T> query( String hql, Object[] paramValues );
	public <T> List<T> query( String hql, String[] paramNames, Object[] paramValues );
	public int queryUpdate(String hql);
	public int querySQLUpdate(String sql);
	public <T> List<T> queryWithPage( String hql, int begin, int fetchSize );
	/**
	 * 通用分页查询
	 * @param hql
	 * @param paramMap
	 * @param page
	 * @return
	 */
	public <T> List<T> queryWithPage( String hql, Map<String, Object> paramMap, PageCond page);
	/**
	 * sql查询
	 * @param sql
	 * @return
	 */
	public List<Object[]> queryWithSQL(String sql ); 
	
	/**
	 * 统计汇兑
	 * @param sql
	 * @return
	 */
	public Object sumWithSQL(String sql );
	
	public int querySQLForCount(String sql );
	public String querySQLUniqueResult(String sql );
	
	/**
	 * 根据主键删除对象
	 * @param clazz
	 * @param pkValue
	 * @return
	 */
	public <T> boolean delete(Class<T> clazz, Serializable pkValue);
	
	/**
	 * 删除对象
	 * @param obj
	 * @return
	 */
	public <T> boolean delete(T obj);
	/**
	 * 新增对象
	 * @param obj
	 * @return
	 */
	public <T> boolean add( T obj );
	/**
	 * 修改对象
	 * @param obj
	 */
	public <T> boolean update( T obj );
	
	/**
	 * 执行sql语句
	 * @param sql
	 * @return sql语句执行影响行数
	 */
	public int excuteBySql(String sql);
	
	/**
	 * 根据sql语句获取Map列表
	 * @param sql
	 * @param clazz
	 * @return
	 */
	public List<Map<String, Object>> queryMapWithSQL(String sql);
	
	/**
	 * 根据sql语句获取对象列表
	 * @param sql
	 * @param clazz
	 * @return
	 */
	public <T> List<T> queryWithSQL(String sql, Class<T> clazz);
    public List<Object[]> queryWithSQLOfBatch(String sql,int beginCount,int batchCount);

}
