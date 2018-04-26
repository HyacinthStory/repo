/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018年4月21日 上午1:12:23
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.vtobank.module.entity.File;

/**
 * @author zhangfb
 * @version 1.0.0.1
 */
public class Test extends SpringTest{
	@Resource(name = "sessionFactory")
    private SessionFactory factory;
	
	@org.junit.Test
    @Transactional(readOnly=false)
    public void testUpdate() {
        Session session =factory.getCurrentSession();
        File f = new File();
        f.setFileName("测试文件zfb");
        session.save(f);
        log.info("-------");
    }
}
