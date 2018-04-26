/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018年4月19日 上午12:00:44
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vtobank.SpringTest;
import com.vtobank.module.common.SpringService;
import com.vtobank.module.entity.File;

/**
 * hibernate事务管理工具测试类
 * @author zhangfb
 * @version 1.0.0.1
 */
public class HibernateHelperTest extends SpringTest{
	
	private static SessionFactory factory;
	private static Session session;
	
	@Before
	public void initSessionFactory() {
		factory = SpringService.getBean("sessionFactory");
		log.info("sessionFactoty:"+factory);
		if (null != factory) {
			// 1. 开启session
			session = factory.openSession();
			log.info("session1:"+session);
			// 2. 需要有@Transactional注解，才能获取到session
			if (session == null) {
				session = factory.getCurrentSession();
				log.info("session2:"+session);
			}
		}
	}
	
	@Test
	public void getSessionTest(){
		HibernateHelper.initSessionFactory();
		Session session = HibernateHelper.getSession();
		File file = (File)session.get(File.class, 32L);
		log.info("file:"+file.toString());
		File f1 = null;
		try {
			f1 = new File();
			f1.setFileName("文件1");
			session.save(f1);
			HibernateHelper.commitAndCloseSession();
		} catch (Exception e) {
			HibernateHelper.rollbackAndCloseSession();
			log.info("操作数据库异常1",e);
		}
		log.info("======1======");
		try {
			session = HibernateHelper.getSession();
			File f2 = new File();
			f2.setFileName("文件2");
			f2.setDelflag("123456789");
			session.save(f2);
			HibernateHelper.commitAndCloseSession();
		} catch (Exception e) {
			HibernateHelper.rollbackAndCloseSession();
			log.info("操作数据库异常2",e);
		} 
		log.info("======2======");
		try {
			session = HibernateHelper.getSession();
			f1.setFileName("测试文件2");
			session.update(f1);
			HibernateHelper.commitAndCloseSession();
		} catch (Exception e) {
			HibernateHelper.rollbackAndCloseSession();
			log.info("操作数据库异常3",e);
		} 
		log.info("======3======");
	}
	
	@After
	public void closeSession () {
		log.info("closeSession");
		//HibernateHelper.closeSession();
	}
}
