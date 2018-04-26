/********************************************
 * 功能说明: 文件配置实体类
 * 模块名称: 公共通用模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2017年10月28日 下午4:40:58
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.system.config;

import com.vtobank.util.JsonMapper;

/**
 * 文件配置实体类
 * 
 * @author zhangfb
 * @version 1.0.0.1
 */
public class FileConfigBean {

	private String whiteList; // 允许上传的文件格式
	private String path;      // 上传文件所在服务器路径
	private String tableName; // 上传操作的表名称
	private String condition; // 条件集合
	private int limitNum;     // 上传文件限制数量
	private String column;    // 上传操作的的字段名
	private boolean customFileName; //允许自定义文件名 
	private String renameType;//自定义文件名类型
	public String getWhiteList() {
		return whiteList;
	}

	public void setWhiteList(String whiteList) {
		this.whiteList = whiteList;
	}

	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public boolean isCustomFileName() {
		return customFileName;
	}

	public void setCustomFileName(boolean customFileName) {
		this.customFileName = customFileName;
	}

	public String getRenameType() {
		return renameType;
	}

	public void setRenameType(String renameType) {
		this.renameType = renameType;
	}

	@Override
	public String toString() {
		return JsonMapper.toJsonString(this);
	}
}
