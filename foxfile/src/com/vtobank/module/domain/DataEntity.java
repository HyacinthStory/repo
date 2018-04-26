/********************************************
 * 功能说明: 数据实体支持类
 * 模块名称: 实体模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉科技技术有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhaorq
 * 开发时间: 2016年12月23日 下午9:15:41
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vtobank.system.annotation.FieldDesc;

/**
 * 数据实体支持类
 * @author zhaorq
 * @version 1.0.0.1
 */
public abstract class DataEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@FieldDesc(desc = "实体编号（唯一标识） ")
	protected String code;
	
	@FieldDesc(desc = "实体名称 ")
	protected String name;
	
	@FieldDesc(desc = "实体类型 ")
	protected String type;
	
	@FieldDesc(desc = "实体状态 ")
	protected String status;
	
	@FieldDesc(desc = "创建时间 ")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date createTime = new Date();
	
	@FieldDesc(desc = "更新时间")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date updateTime;
	
	/** 实体校验码 */
	@JsonIgnore
	protected String checkValue;
	
	/** 分页页码 */
	@JsonIgnore
	protected Integer pageNum = 1;
	
	/** 每页数量 */
	@JsonIgnore
	protected Integer pageSize = 20;
	
	public DataEntity() {
		super();
	}
	public DataEntity(String code) {
		super();
		this.code = code;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCheckValue() {
		return checkValue;
	}
	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
