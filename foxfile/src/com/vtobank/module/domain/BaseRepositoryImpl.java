/********************************************
 * 功能说明: 持久层基础类
 * 模块名称: 公共模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2017年11月12日 上午3:14:18
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.domain;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vtobank.system.entity.PageCond;
import com.vtobank.util.XaUtils;

/**
 * 持久层基础类
 * 
 * @author zhangfb
 * @version 1.0.0.1
 */
@SuppressWarnings("all")
public class BaseRepositoryImpl<T extends Serializable, ID extends Serializable> implements BaseRepository<T, ID> {

	@Resource(name = "sessionFactory")
	private SessionFactory factory;

	private Class<T> persistClass;

	@SuppressWarnings("unchecked")
	public BaseRepositoryImpl() {
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        persistClass = (Class<T>) type.getActualTypeArguments()[0];
	}

	/**
	 * 从当前线程中获取session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return factory.getCurrentSession();
	}

	/**
	 * 增
	 */
	@Override
	public T save(T t) {
		getSession().save(t);
		return t;
	}

	/**
	 * 删
	 */
	@Override
	public T delete(T t) {
		getSession().delete(t);
		return t;
	}

	/**
	 * 根据id删除
	 * @param id 
	 * @see com.vtobank.module.domain.BaseRepository#deleteById(java.io.Serializable) 
	 */ 
	@Override
	public void deleteById(ID id) {
		T t = getById(id);
        if(t != null){
            this.getSession().delete(t);
        }
	}

	
	/**
	 * 修改
	 */
	@Override
	public T update(T t) {
		getSession().update(t);
		return t;
	}
	
	/**
	 * 获得对象时加锁
	 * @param t
	 * @param id
	 * @return 
	 * @see com.vtobank.module.domain.BaseRepository#getWithLock(java.io.Serializable, java.io.Serializable) 
	 */ 
	@Override
	public T getWithLock(ID id) {
		return (T)getSession().get(persistClass, id, LockOptions.UPGRADE);
	}

	/** 
	 * 根据查询语句获取对象(取第一个)
	 * @param hql
	 * @return 
	 * @see com.vtobank.module.domain.BaseRepository#get(java.lang.String) 
	 */ 
	@Override
	public T get(String hql) {
		List<T> list = queryWithHqlByPage(hql, null, null);
		if(XaUtils.isEmpty(list)){
			return null;
		}else{
			return list.get(0);
		}
	}

	/**
	 * 根据对象的unique字段获取对象
	 * @param t
	 * @param attrName
	 * @param attrValue
	 * @return 
	 * @see com.vtobank.module.domain.BaseRepository#getUniqueResult(java.io.Serializable, java.lang.String, java.io.Serializable)
	 */
	@Override
	public T getUniqueResult(String attrName, Serializable attrValue) {
		String hql = "from " + persistClass.getSimpleName() + " where " + attrName  + " = ? ";
		Query query = getSession().createQuery( hql );
		query.setParameter(0, attrValue);
		return (T) query.uniqueResult();
	}

	/**
	 * 根据条件集合获取对象
	 * @param hql
	 * @param paramMap
	 * @return 
	 * @see com.vtobank.module.domain.BaseRepository#getUniqueResult(java.lang.String, java.util.Map) 
	 */ 
	@Override
	public T getUniqueResult(String hql, Map<String, Object> paramMap) {
		Query query = getSession().createQuery(hql);
		if( paramMap != null && paramMap.size() > 0 ){
			for(String key : paramMap.keySet()){
				query.setParameter(key, paramMap.get(key));
			}
		}
		return (T) query.uniqueResult();
	}
	
	/**
	 * 分页
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> selectByExample(T t, int... pages) {
		Criteria c = getSession().createCriteria(persistClass);
		if (t != null) {
			c.add(Example.create(t).enableLike());
		}
		if (pages != null && pages.length > 0) {
			c.setFirstResult(pages[0]);
		}
		if (pages != null && pages.length > 1) {
			c.setMaxResults(pages[1]);
		}
		return c.list();
	}

	/**
	 * 总条数
	 */
	@Override
	public int selectByExampleRowsNum(T t) {
		DetachedCriteria dc = this.getCriteria(t);
        Criteria c = dc.getExecutableCriteria(this.getSession());
        c.setProjection(Projections.rowCount());
        return ((Number) c.uniqueResult()).intValue();
	}

	/**
	 * 查询且修改（hql）
	 */
	@Override
	public int queryUpdate(String hql) {
		return getSession().createQuery(hql).executeUpdate();
	}

	/**
	 * 查询且修改（sql）
	 */
	@Override
	public int querySQLUpdate(String sql) {
		return getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * @param id
	 * @return 
	 * @see com.vtobank.module.domain.BaseRepository#getById(java.io.Serializable) 
	 */ 
	@Override
	public T getById(ID id) {
		return (T) this.getSession().get(persistClass,id);
	}

	 /**
     * 有条件查询多条数据
     * @param hql 	查询语句
     * @param startIndex 	起始下标
     * @param dataNum 	数据数目
     * @return
	 * @see com.vtobank.module.domain.BaseRepository#query(java.lang.String, java.lang.Integer, java.lang.Integer) 
	 */ 
	@Override
	public List<T> query(String hql, Integer startIndex, Integer dataNum) {
		Query query = this.getSession().createQuery(hql);
        if (startIndex != null && startIndex > 0) {
            query.setFirstResult(startIndex);
        }
        if (dataNum != null && dataNum > 0) {
            query.setMaxResults(dataNum);
        }
        return query.list();
	}

	/**
	 * 根据hql语句获取对象集合
	 * @param hql
	 * @param paramMap
	 * @param page
	 * @return 
	 * @see com.vtobank.module.domain.BaseRepository#queryWithHqlByPage(java.lang.String, java.util.Map, com.vtobank.system.entity.PageCond) 
	 */ 
	@Override
	public List<T> queryWithHqlByPage(String hql, Map<String, Object> paramMap, PageCond page) {
		Query query = this.getSession().createQuery(hql);
        if (!XaUtils.isEmpty(paramMap)) {
            for (String key : paramMap.keySet()) {
                query.setParameter(key, paramMap.get(key));
            }
        }
        if (page != null) {
            page.setCount(XaUtils.isEmpty(query.list()) ? 0 : query.list().size());
            query.setFirstResult(page.getCurrentPage() * page.getLength());
            query.setMaxResults(page.getLength());
        }
        return query.list();
	}

	/**
	 * 根据sql语句获取Map列表
	 * @param sql
	 * @param page
	 * @return 
	 * @see com.vtobank.module.domain.BaseRepository#queryMapWithSQLAndPage(java.lang.String, com.vtobank.system.entity.PageCond) 
	 */ 
	@Override
	public List<Map<String, Object>> queryMapWithSQLAndPage(String sql, PageCond page) {
		List<Map<String, Object>> rowSet = new ArrayList<Map<String, Object>>();
        Query query = getSession().createSQLQuery(sql);
        query.setFirstResult(page.getCurrentPage() * page.getLength());
        query.setMaxResults(page.getLength());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map> list = query.list();
        if (!XaUtils.isEmpty(list)) {
            for (Map<String, Object> map : list) {
                rowSet.add(map);
            }
        }
        return rowSet;
	}

	/**
	 * 样例查询
	 * @param t
	 * @param page
	 * @return 
	 * @see com.vtobank.module.domain.BaseRepository#selectByExample(java.io.Serializable, com.vtobank.system.entity.PageCond) 
	 */ 
	@Override
	public List<T> selectByExample(T t, PageCond page) {
		DetachedCriteria dc = this.getCriteria(t);
        Criteria c = dc.getExecutableCriteria(this.getSession());
        if (page != null) {
            c.setFirstResult(page.getCurrentPage() * page.getLength());
        }
        if (page != null) {
            c.setMaxResults(page.getLength());
        }
        return c.list();
	}

	/**
	 * 将对象从session的缓存中清除
	 * @param t
	 * @return 
	 * @see com.vtobank.module.domain.BaseRepository#evict(java.io.Serializable) 
	 */ 
	@Override
	public boolean evict(T t) {
		this.getSession().evict(t);
		return true;
	}

	/**
	 * 获取DetachedCriteria对象
	 * @param record
	 * @return DetachedCriteria
	 */
	protected DetachedCriteria getCriteria(T record) {
        DetachedCriteria dc = DetachedCriteria.forClass(persistClass);
        if (record != null) {
            dc.add(Example.create(record).enableLike());
        }
        return dc;
    }
}
