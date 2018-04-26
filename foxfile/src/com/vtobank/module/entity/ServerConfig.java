/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018年4月19日 下午1:33:44
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.entity;

import com.vtobank.module.domain.BaseEntity;
import com.vtobank.system.annotation.ClassDesc;
import com.vtobank.system.annotation.FieldDesc;
import com.vtobank.util.JsonMapper;

/**
 * 服务器配置实体类
 * @author zhangfb
 * @version 1.0.0.1
 */
@ClassDesc(desc="服务器配置")
public class ServerConfig extends BaseEntity{

	private static final long serialVersionUID = 5681153479887364752L;
	@FieldDesc(desc = "服务器编码")
	private String code;
	@FieldDesc(desc = "服务器名称")
	private String serverName;
	@FieldDesc(desc = "服务器域名")
	private String domainName;
	@FieldDesc(desc = "服务器ip")
	private String serverIp;
	@FieldDesc(desc = "端口号")
	private Integer serverPort;
	@FieldDesc(desc = "服务器用途")
	private String useType;
	@FieldDesc(desc = "工程名称")
	private String appName;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	@Override
	public String toString() {
		return JsonMapper.toJsonString(this);
	}
}
