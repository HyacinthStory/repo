/********************************************
 * 功能说明: 平台初始化Listener
 * 模块名称: 平台Listener模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉科技技术有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2017年10月25日 下午9:15:41
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.system.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.vtobank.database.HibernateHelper;
import com.vtobank.module.common.SpringService;
import com.vtobank.system.config.FileConfigContext;
import com.vtobank.util.Global;


/**
 * 平台初始化Listener
 * @author zhangfb
 * @version 1.0.0.1
 */
public class WebContextListener extends ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		WebApplicationContext appContext = super.initWebApplicationContext(servletContext);
		// 打印欢迎信息
		printWelcomeMsg();
		// 设置应用上下文
		SpringService.setAppContext(appContext);
		// 初始化sessionFactory
		HibernateHelper.initSessionFactory();
		// 初始化文件配置参数
		FileConfigContext.initFileConfig();
		
		return appContext;
	}
	
	/**
	 * 打印欢迎信息
	 */
	private static void printWelcomeMsg(){
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n\t 欢迎使用 "+Global.getConfig("product.name")+"  - Powered By http://www.aghoo.com\r\n");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());
	}

}
