/**
 * @author   zhangyp
 * @created  2013-9-25 下午4:45:50
 * @email    zviolet@163.com
 * TODO      基础访问代码
 */
package com.vtobank.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.vtobank.system.entity.PageCond;
import com.vtobank.util.XaUtils;
@SuppressWarnings("all")
public class DataSet implements IDataSet {
	
	static Logger log = Logger.getLogger( DataSet.class );
	
	public DataSet(){
	}
	
	/*----  session控制  ----- */
	@Override
	public boolean beginSession() {
		return false;
	}

	@Override
	public boolean closeSession() {
		return false;
	}

	@Override
	public boolean commitAndCloseSession() {
		return false;
	}

	@Override
	public boolean rollbackAndCloseSession() {
		return false;
	}
	
	public Session getSession(){
		return HibernateHelper.getSession();
	}
	
	@Override
	public <T> T getUniqueResult(Class<T> clazz, String attrName, Serializable attrValue ){
		String hql = "from " + clazz.getSimpleName() + " where " + attrName  + " = ? ";
		Query query = getSession().createQuery( hql );
		query.setParameter(0, attrValue);
		return (T)query.uniqueResult();
	}
	
	@Override
	public <T> T getUniqueResult(String hql, Map<String, Object> paramMap ){
		Query query = getSession().createQuery( hql );
		if( paramMap != null && paramMap.size() > 0 ){
			for( String key : paramMap.keySet() ){
				query.setParameter(key, paramMap.get(key) );
			}
		}
		return (T)query.uniqueResult();
	}
	
	@Override
	public <T> T get(Class<T> clazz, Serializable pkValue){			
		return (T)getSession().get( clazz, pkValue );
	}
	
	@Override
	public <T> T getWithLock(Class<T> clazz, Serializable pkValue){	
		
		return (T)getSession().get(clazz, pkValue, LockMode.UPGRADE );
	}

	@Override
	public <T> T get(String hql) {
		List<T> list = query( hql );
		if(XaUtils.isEmpty(list ) ){
			return null;
		}else{
			return list.get(0);
		}
	}
	
	public <T> List<T> query( Class<T> clazz ){
		return query("from " + clazz.getSimpleName() );
	}

	@Override
	public <T> List<T> query(String hql) {		
		return getSession().createQuery( hql ).list();
	}
	
	@Override
	public <T> List<T> query(String hql, int maxCount) {
		return getSession().createQuery( hql ).setMaxResults(maxCount).list();
	}
	
	

	@Override
	public <T> List<T> query(String hql, Object[] paramValues) {
		Query query = getSession().createQuery( hql );
		if(!XaUtils.isEmpty(paramValues)){
			for( int i = 0; i < paramValues.length; i++ ){
				query.setParameter(i, paramValues[i]);
			}
		}
		return query.list();
	}

	@Override
	public <T> List<T> query(String hql, String[] paramNames, Object[] paramValues) {
		Query query = getSession().createQuery( hql );
		if( !XaUtils.isEmpty( paramNames ) ){
			for(int i = 0; i < paramNames.length; i++){
				query.setParameter( paramNames[i], paramValues[i] );
			}
		}		
		return query.list();
	}
	
	@Override
	public int queryUpdate(String hql) {		
		return getSession().createQuery( hql ).executeUpdate();
	}
	
	@Override
	public int querySQLUpdate(String sql) {		
		return getSession().createSQLQuery( sql ).executeUpdate();
	}

	@Override
	public <T> List<T> queryWithPage(String hql, int begin, int fetchSize) {
		Query query = getSession().createQuery( hql );
		query.setFirstResult( begin );
		query.setMaxResults( fetchSize );
		//query.setFetchSize( fetchSize );
		return query.list();
	}
	
	@Override
	public <T> List<T> queryWithPage( String hql, Map<String, Object> paramMap, PageCond page){
		if( page == null ){
			if( paramMap == null || paramMap.size() == 0 ){
				return query(hql);
			}else{
				String[] paramNames = new String[ paramMap.size() ];
				Object[] paramValues = new Object[ paramMap.size() ];
				int k = 0;
				for( String key : paramMap.keySet() ){
					paramNames[k] = key;
					paramValues[k] = paramMap.get(key);
					k++;
				}
				return query(hql, paramNames, paramValues );
			}
		}
		Query query = getSession().createQuery( "select count(id) " +  hql );
		if( paramMap != null && paramMap.size() > 0 ){
			for( String key : paramMap.keySet() ){
				query.setParameter(key, paramMap.get(key) );
			}
		}
		Integer count = 0;
		List list = query.list();
		if( list != null && list.size() > 0 ){
			count = Integer.parseInt( String.valueOf( list.get(0) ) );
		}
		log.debug("hql : " + hql + ",count : " + count );
		if( count > 0 ){
			page.setCount(count);
			query = getSession().createQuery( hql );
			if( paramMap != null && paramMap.size() > 0 ){
				for( String key : paramMap.keySet() ){
					query.setParameter(key, paramMap.get(key) );
				}
			}
			query.setFirstResult( page.getCurrentPage() * page.getLength() );
			query.setMaxResults( page.getLength() );
			return query.list();
		}else{
			return null;
		}		
	}
	
	public List<Object[]> queryWithSQL(String sql ){
		List list = getSession().createSQLQuery( sql ).list();
		return list;
	}
	
	public Object sumWithSQL(String sql ){
		List<Object[]> list = queryWithSQL( sql );
		for( Object obj : list ){
			return obj;
		}
		return null;
	}
	
	public int querySQLForCount(String sql ){
		Query query = getSession().createSQLQuery( sql );
		String result = String.valueOf(query.uniqueResult());
		return Integer.valueOf(result);
	}
	
	//sql查询  唯一值
	public String querySQLUniqueResult(String sql ){
		Query query = getSession().createSQLQuery( sql );
		String result = String.valueOf(query.uniqueResult());
		return result;
	}

	@Override
	public <T> boolean delete(Class<T> clazz, Serializable pkValue) {
		T t = get( clazz, pkValue );
		if( t != null ){
			getSession().delete( t );
		}
		return true;
	}
	
	@Override
	public <T> boolean delete(T obj) {
		if( obj != null ){
			getSession().delete( obj );
		}
		return true;
	}

	@Override
	public <T> boolean add(T obj) {
		if( obj != null ){
			getSession().save( obj );
		}
		return true;
	}

	@Override
	public <T> boolean update(T obj) {
		if( obj != null ){
			getSession().update( obj );
		}
		return true;
	}

	@Override
	public int excuteBySql(String sql) {
		SQLQuery query = getSession().createSQLQuery(sql);
		return query.executeUpdate();
	};
	
	@Override
	public List<Map<String, Object>> queryMapWithSQL(String sql){
		List<Map<String, Object>> rowSet = new ArrayList<Map<String, Object>>();
		List<Map> list = getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		for (Map<String, Object> map : list) {
			rowSet.add(map);
		}
		return rowSet;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> queryWithSQL(String sql, Class<T> clazz){
		List<T> list = getSession().createSQLQuery(sql).addEntity(clazz).list();
		return list;
	}

    @Override
    public List<Object[]> queryWithSQLOfBatch(String sql,int beginCount,int batchCount){
        Query query = getSession().createSQLQuery( sql );
        query.setFirstResult( beginCount );
        query.setMaxResults( batchCount );
        return query.list();
    }

}
