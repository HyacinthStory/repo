package com.vtobank.module.entity;

import java.util.Date;

import com.vtobank.module.domain.BaseEntity;
import com.vtobank.system.annotation.ClassDesc;
import com.vtobank.system.annotation.FieldDesc;
import com.vtobank.util.JsonMapper;

/**
 * 文件实体表
 * @author zhangfb
 * @version 1.0.0.1
 */
@ClassDesc(desc = "文件表")
public class File extends BaseEntity {
 
	// @Fields serialVersionUID : 根据类名、方法、变量等生成的hash值  
	private static final long serialVersionUID = -1168233408063463885L;
	
	@FieldDesc(desc = "文件原名称")
	private String fileName;
	@FieldDesc(desc = "文件上传名称")
	private String uploadName;
	@FieldDesc(desc = "文件长传路径")
	private String filePath;
	@FieldDesc(desc = "文件类型")
	private String fileType;
	@FieldDesc(desc = "删除标志")
	private String delflag;
	@FieldDesc(desc = "创建时间")
	private Date createTime;
	@FieldDesc(desc = "实体名称")
	private String entityName;
	@FieldDesc(desc = "实体id")
	private Long entityId;
	@FieldDesc(desc = "实体编码")
	private String entityCode;
	@FieldDesc(desc = "文件用途")
	private String useType;
	@FieldDesc(desc = "银行编码")
	private String bankCode;
	@FieldDesc(desc = "银行id")
	private Long bankId;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUploadName() {
		return uploadName;
	}

	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getEntityCode() {
		return entityCode;
	}

	public void setEntityCode(String entityCode) {
		this.entityCode = entityCode;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	@Override
	public String toString() {
		return JsonMapper.toJsonString(this);
	}
}
