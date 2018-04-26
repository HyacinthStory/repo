/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018年4月19日 上午1:06:10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.service;

import com.vtobank.module.entity.ServerConfig;

/**
 * 服务器配置服务类
 * @author zhangfb
 * @version 1.0.0.1
 */
public interface ServerConfigService {
	
	ServerConfig getByUseType(String serverUseType);
}
