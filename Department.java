package com.hyacinth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 组织机构实体类
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "base_department")
@EqualsAndHashCode(callSuper = true)
public class Department extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 组织名称 */
    @Column(
            name = "dept_name",
            nullable = false,
            length = 32
    )
    private String deptName;

    /** 组织级别 */
    @Column(
            name = "dept_level",
            nullable = false,
            length = 2
    )
    private String deptLevel;

    /** 上级组织编码 */
    @Column(
            name = "parent_dept_id",
            nullable = false,
            length = 64

    )
    private String parentDeptId;

    /** 组织元素编号 */
    @Column(
            name = "dept_element_code",
            nullable = false,
            length = 64
    )
    private String deptElementCode;
}
