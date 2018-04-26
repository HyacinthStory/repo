/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018年4月19日 上午11:39:58
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.vtobank.SpringTest;
import com.vtobank.module.entity.File;

/**
 * 
 * @author zhangfb
 * @version 1.0.0.1
 */
public class FileServiceImplTest extends SpringTest{
	@Autowired
	private FileService fileService;
	
	@Test
	public void getTest(){
		File file = fileService.get(25L);
		log.info("file:"+file);
	}
	
	@Test
	public void saveTest(){
		File f = new File();
		f.setFileName("myfile123");
		fileService.save(f);
	}
	
	@Transactional(readOnly=false)
	@Test
	public void saveUpdate(){
		File f = new File();
		f.setFileName("test文件1");
		File save = fileService.save(f);
		save.setUploadName("testUpload");
		save.setDelflag("1231231");
		File update = fileService.update(save);
		log.info("file"+update);
	}
}
