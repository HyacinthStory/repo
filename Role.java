package com.hyacinth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 角色表
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "base_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** 角色名称 */
    @Column(
            name = "role_name",
            nullable = false,
            length = 20
    )
    private String roleName;
}
