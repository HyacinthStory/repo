/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/8/21 23:09
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "base_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** 用户名 */
    @Column(
            name = "user_name",
            nullable = false,
            length = 20
    )
    private String userName;

    /** 密码 */
    @Column(
            name = "pass_word",
            nullable = false,
            length = 64
    )
    private String passWord;

    /** 真实姓名 */
    @Column(
            name = "real_name",
            length = 10
    )
    private String realName;

    /** 手机号 */
    @Column(
            name = "phone_num",
            nullable = false,
            length = 20
    )
    private String phoneNum;

    /** 邮箱地址 */
    @Column(
            name = "email",
            length = 32
    )
    private String email;

    /** 证件类型 */
    @Column(
            name = "id_type",
            length = 2
    )
    private String idType;

    /** 证件号 */
    @Column(
            name = "id_num",
            length = 20
    )
    private String idNum;

    /** 申请时间 */
    @Column(
            name = "apply_time"
    )
    @Temporal(TemporalType.TIMESTAMP)
    private Date applyTime;

    /** 审核时间 */
    @Column(
            name = "audit_time"
    )
    @Temporal(TemporalType.TIMESTAMP)
    private Date auditTime;

    /** 审核状态 */
    @Column(
            name = "audit_flag",
            length = 2
    )
    private String auditFlag;

    /** 审核人编码 */
    @Column(
            name = "audit_id",
            length = 64
    )
    private String auditId;

    /** 部门编码 */
    @Column(
            name = "dept_id",
            length = 64
    )
    private String deptId;
}
