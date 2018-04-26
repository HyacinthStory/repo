package com.vtobank.module.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vtobank.system.constant.ITag;

/**
 * 主页控制器类
 * @author zhangfb
 * @version 1.0.0.1
 */
@Controller
public class HomeController {

	/** 系统主页 */
	@RequestMapping(value = "index.do")
	public String index() {
		return ITag.indexPage;
	}

	/** 上传测试主页 */
	@RequestMapping(value = "testUp.do")
	public String testUpload() {
		return ITag.uploadTestPage;
	}
}
