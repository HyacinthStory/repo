/********************************************
 * 功能说明: 业务处理异常类
 * 模块名称: 异常模块
 * 系统名称: 互联网前置平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhaorq
 * 开发时间: 2017年5月10日 下午3:03:41
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.system.exception;

/**
 * 业务处理异常类
 * @author zhaorq
 * @verion 1.0.0.1
 */
public class BizException extends RuntimeException {
	
	static final long serialVersionUID = 1L;
	
	/** 错误码 */
	private String errNo;
	/** 错误信息 */
	private String errMsg;
	
	public BizException() {
		super();
	}
	public BizException(String errNo) {
		super();
		this.errNo = errNo;
	}
	public BizException(String errNo, String errMsg) {
		super();
		this.errNo = errNo;
		this.errMsg = errMsg;
	}
	
	public String getErrNo() {
		return errNo;
	}
	public void setErrNo(String errNo) {
		this.errNo = errNo;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public String toString() {
		return "{错误码：" + this.errNo + "，错误信息：" + this.errMsg + "}";
	}

}
