/********************************************
 * 功能说明: 应用上下文静态服务类
 * 模块名称: 平台服务模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhaorq
 * 开发时间: 2017年5月5日 下午2:12:25
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.common;

import javax.sql.DataSource;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 应用上下文静态服务类
 * @author zhaorq
 * @verion 1.0.0.1
 */
@Service
@Lazy(false)
public class SpringService implements ApplicationContextAware, DisposableBean {
	
	/** 应用上下文 */
	private static ApplicationContext appContext = null;
	
	/**
	 * 设置应用上下文
	 */
	public static void setAppContext(ApplicationContext appContext) {
		SpringService.appContext = appContext;
	}
	
	/**
	 * 获取应用上下文
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return appContext;
	}
	
	/**
	 * 从应用上下文中取得Bean, 自动转型为所赋值对象的类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		if (name.startsWith("com.vtobank.")) {
			try {
				Class<?> clazz = Class.forName(name);
				return (T) appContext.getBean(clazz);
			} catch (ClassNotFoundException e) {
				return null;
			}
		}
		return (T) appContext.getBean(name);
	}
	
	/**
	 * 从应用上下文中取得Bean, 自动转型为所赋值对象的类型
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return appContext.getBean(requiredType);
	}
	
	/**
	 * 获取默认数据源
	 */
	public static DataSource getDataSource() {
		assertContextInjected();
		return (DataSource) appContext.getBean("dataSource");
	}
	
	/**
	 * 清除应用上下文
	 */
	public static void clearHolder() {
		appContext = null;
	}

	/**
	 * 实现DisposableBean接口, 在Context关闭时清理静态变量
	 */
	@Override
	public void destroy() throws Exception {
		SpringService.clearHolder();
	}

	/**
	 * 实现ApplicationContextAware接口, 注入Context到静态变量中
	 */
	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		SpringService.appContext = appContext;
	}
	
	/**
	 * 检查应用上下文不为空
	 */
	private static void assertContextInjected() {
		if (appContext == null) {
			appContext = new FileSystemXmlApplicationContext("classpath:appContext.xml");
		}
		
		Validate.validState(appContext != null, "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
	}

}
