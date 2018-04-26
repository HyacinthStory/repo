package com.vtobank.database;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.vtobank.module.common.SpringService;

/**
 * hibernate事务管理工具类
 * @author zhangfb
 * @version 1.0.0.1
 */
public class HibernateHelper{
	
	private static Logger log = Logger.getLogger(HibernateHelper.class);
	
	//本地线程存放session
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<>();

	private static SessionFactory factory;
	
	/**
	 * 初始化sessionFactory
	 */
	public static void initSessionFactory(){
		log.info("初始化sessionFactory。。。");
		factory = SpringService.getBean("sessionFactory");
	}

	/**
	 * 获取sessionFactory
	 * @return SessionFactory
	 */
	public static SessionFactory getSessionFactory(){
		return factory;
	}
	
	/**
	 * 在请求开始时清除以前残余的session对象
	 */
	public static void cleanSession(){
		Session session = threadLocal.get();
		if(session != null){
			if(session.isOpen()){
				session.close();
			}
		}
		threadLocal.remove();
	}
	
	//返回true表明是本次调用开的session,返回false表明前面已经有程序打开了session
	public static boolean beginSession(){
		Session session = threadLocal.get();
		if(session == null || !session.isOpen()){
			session = factory.openSession();
			session.beginTransaction();
			threadLocal.set(session);
			return true;
		}
		return false;
	}

	public static Session getSession(){
		Session session = threadLocal.get();
		if(session == null || !session.isOpen()){
			session = factory.openSession();
			session.beginTransaction();
			threadLocal.set(session);
		}
		return session;
	}
	
	public static boolean commitAndCloseSession() {
		Session session = threadLocal.get();
		if (session != null) {
			if (session.isOpen() && session.getTransaction().isActive()) {
				session.getTransaction().commit();
				session.close();
				threadLocal.remove();
				return true;
			}
			threadLocal.remove();
		}
		return false;
	}

	public static boolean closeSession() {
		Session session = threadLocal.get();
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
			threadLocal.remove();
		}
		return true;
	}

	public static boolean rollbackAndCloseSession() {
		Session session = threadLocal.get();
		if (session != null) {
			if (session.isOpen() && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
				session.close();
				threadLocal.remove();
				return true;
			}
			threadLocal.remove();
		}
		return false;
	}

	public static void closeSession(Session session) {
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
			threadLocal.remove();
		}
	}
	
}
