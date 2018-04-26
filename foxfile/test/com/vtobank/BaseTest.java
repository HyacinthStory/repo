/********************************************
 * 功能说明: Junit测试基类
 * 模块名称: Junit测试模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhaorq
 * 开发时间: 2017年7月12日 上午11:39:19
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank;

import org.apache.log4j.Logger;

/**
 * Junit测试基类
 * @author zhanfb
 * @version 1.0.0.1
 */
public abstract class BaseTest {
	
	/**
	 * 日志对象
	 */
	protected Logger log = Logger.getLogger(this.getClass());

}
