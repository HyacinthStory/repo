package com.hyacinth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class Description:用户角色实体类
 *
 * @Version v1.0 2017/4/21 20:28
 * @JDK version 1.8.0_51
 * @Author zfb
 */

@Data
@Entity
@Table(name = "base_user_role")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** 用户编码 */
    @Column(
            name = "user_id",
            nullable = false,
            length = 64
    )
    private String userId;

    /** 角色编码 */
    @Column(
            name = "role_code",
            nullable = false,
            length = 64
    )
    private String roleId;
}
