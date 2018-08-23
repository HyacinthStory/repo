package com.hyacinth.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 资源实体类
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Data
@Entity
@Table(name = "base_resource")
@EqualsAndHashCode(callSuper = true)
public class Resource extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /** 上级资源编码 */
    @Column(
            name = "parent_res_id",
            length = 64
    )
    private String parentResId;

    /** 资源名称 */
    @Column(
            name = "res_name",
            nullable = false,
            length = 64
    )
    private String resName;

    /** 资源类型 */
    @Column(
            name = "res_type",
            nullable = false,
            length = 2
    )
    private String resType;

    /** 资源链接 */
    @Column(
            name = "res_url",
            length = 128
    )
    private String resUrl;

    /** 菜单图标 */
    @Column(
            name = "icon",
            length = 32
    )
    private String icon;

    /** 资源元素编码 */
    @Column(
            name = "res_element_code",
            nullable = false,
            length = 64
    )
    private String resElementCode;

    /** 是否显示 */
    @Column(
            name = "is_show",
            nullable = false,
            length = 1,
            columnDefinition = "varchar(1) default '1'"
    )
    private String isShow;

    /** 资源级别 */
    @Column(
            name = "level",
            nullable = false,
            length = 2
    )
    private String level;

    /** 排序 */
    @Column(
            name = "res_sort"
    )
    private Integer resSort;
}
