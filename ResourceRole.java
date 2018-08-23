package com.hyacinth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 资源角色实体类
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "base_resource_role")
@EqualsAndHashCode(callSuper = true)
public class ResourceRole extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** 资源编码 */
    @Column(
            name = "res_id",
            nullable = false,
            length = 64
    )
    private String resId;

    /** 角色编码 */
    @Column(
            name = "role_id",
            nullable = false,
            length = 64
    )
    private String roleId;

}
