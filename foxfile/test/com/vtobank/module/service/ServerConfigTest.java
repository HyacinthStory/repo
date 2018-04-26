/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018年4月19日 下午10:00:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.vtobank.SpringTest;
import com.vtobank.module.entity.ServerConfig;

/**
 * @author zhangfb
 * @version 1.0.0.1
 */
public class ServerConfigTest extends SpringTest{

	@Autowired
	private ServerConfigService serverConfigService;
	
	@Test
	public void getByUseTypeTest(){
		ServerConfig serverConfig = serverConfigService.getByUseType("07");
		log.info("serverConfig:"+serverConfig);
	}
}
