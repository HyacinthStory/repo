/**
 * @author   zhangyp
 * @created  2013-10-8 下午3:47:05
 * @email    zviolet@163.com
 * TODO
 */
package com.vtobank.database;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;

public class DataSetProxy implements InvocationHandler {
	
	  static Logger log = Logger.getLogger( DataSetProxy.class );

	  protected Object delegate;
	  
	  private static DataSetProxy proxy = null;
	  
	  public static DataSetProxy getInstance(){
		  if( proxy == null ){
			  proxy = new DataSetProxy();
		  }
		  return proxy;
	  }

	  public Object bind(Object delegate ){
	    this.delegate = delegate;
	    return Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(), this.delegate.getClass().getInterfaces(), this);
	  }

	  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	    if ("beginSession".equals(method.getName())){
	    	return Boolean.valueOf(before());
	    }else if ("commitAndCloseSession".equals(method.getName())) {
	        return Boolean.valueOf(after());
	    }else if (("closeSession".equals(method.getName())) ){
	    	return HibernateHelper.closeSession();
	    }else if ( ("rollbackAndCloseSession".equals(method.getName()))){
	       return HibernateHelper.rollbackAndCloseSession();
	    }
	    boolean openSession = before();
	    Object result = method.invoke(this.delegate, args);//com.vtobank.comm.dao.DataSet,from LocalConfig
	    if (openSession) {
	       after();
	    }
	    return result;
	  }

	  protected boolean before() {		  
	     return HibernateHelper.beginSession();
	  }

	  protected boolean after(){
	     return HibernateHelper.commitAndCloseSession();
	  }

}
