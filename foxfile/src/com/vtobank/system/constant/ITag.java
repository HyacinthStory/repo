/********************************************
 * 功能说明: 标签常量类
 * 模块名称: 常量模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhaorq
 * 开发时间: 2017年6月1日 上午11:33:26
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.system.constant;

/**
 * 标签常量类
 * @author zhangfb
 * @version 1.0.0.1
 */
public interface ITag {
	
	/** UTF-8 */
	String UTF_8 = "UTF-8";
	
	/** 成功 */
	String Success = "S";
	/** 失败 */
	String Failure = "F";
	
	/** 用户编码 */
	String UserCode = "UserCode";
	String ShopCode = "ShopCode";
	
	/** 当前用户 */
	String CurrentUser = "CurrentUser";
	/** 需要验证码 */
	String NeedCaptcha = "NeedCaptcha";
	/** 验证码编码 */
	String CaptchaCode = "CaptchaCode";
	/** 手机验证码 */
	String MobileCaptcha = "MobileCaptcha";
	/** 连续失败次数 */
	String FailureTimes = "FailureTimes";
	
	/** 错误页跳转路径 */
	String errorPage="common/error";
	/** 上传测试页面跳转路径 */
	String uploadTestPage="common/uploadTest";
	/** 首页跳转路径 */
	String indexPage = "common/index";
	/** 文件类型 */
	public interface fileType {
		String FTImg = "01";
		String FTAttach = "02";
	}
	
	/** 自定义文件名类型 */
	public interface renameType {
		String RTFile = "01";
		String RTField = "02";
	}
	
	/** 文件配置前缀 */
	public interface fileConfigPrefix {
		String IMG_ = "img_";
		String ATTM_ = "attm_";
	}
	
	/** 公共配置参数 */
	public interface commConfig {
		String FileViewDir = "fileViewDir";
		String FileUploadDir = "fileUploadDir";
		String FileDownloadDir = "fileDownloadDir";
		String UseHttps = "useHttps";
	}
	
	/** YesOrNo */
	public interface yesOrNo {
		String Yes = "1";
		String No = "0";
	}
}
