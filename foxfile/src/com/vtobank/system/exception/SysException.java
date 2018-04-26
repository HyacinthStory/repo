/********************************************
 * 功能说明: 系统异常类
 * 模块名称: 异常模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhaorq
 * 开发时间: 2018年2月8日 下午7:18:49
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.system.exception;

/**
 * 系统异常类
 * @author zhaorq
 * @verion 1.0.0.1
 */
public class SysException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/** 错误码 */
	protected String errNo;
	/** 错误信息 */
	protected String errMsg;
	
	public SysException() {
		super();
	}
	public SysException(String errNo) {
		super();
		this.errNo = errNo;
	}
	public SysException(String errNo, String errMsg) {
		super();
		this.errNo = errNo;
		this.errMsg = errMsg;
	}
	
	public SysException(Throwable cause) {
        super(cause);
    }
	
	public SysException(String message, Throwable cause) {
        super(message, cause);
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
		return getMessage();
	}
	
	public String getMessage() {
		return "{错误码：" + this.errNo + "，错误信息：" + this.errMsg + "}";
	}

}
