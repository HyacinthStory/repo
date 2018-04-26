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

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Junit测试基类
 * @author zhangfb
 * @version 1.0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class) //用于配置spring中测试的环境
@ContextConfiguration(locations="classpath:appContext.xml") //用于指定配置文件所在的位置
public abstract class SpringTest extends BaseTest {	
	
}
