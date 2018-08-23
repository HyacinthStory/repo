package com.hyacinth.entity;


import com.hyacinth.common.SerialUtils;
import com.hyacinth.common.constant.IDict;
import com.hyacinth.json.JsonMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 * @author zhaorq
 * @version 1.0.0.1
 */
@Data
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键标识
     * –IDENTITY：采用数据库 ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
     * –AUTO： JPA自动选择合适的策略，是默认选项；
     * –SEQUENCE：通过序列产生主键，通过 @SequenceGenerator 注解指定序列名，MySql 不支持这种方式
     * –TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植
     */
    @Id
    @Column(
            name = "id",
            unique = true,
            nullable = false,
            updatable = false
    )
    private String id;

    /** 数据状态 */
    @Column(
            name = "data_flag",
            nullable = false,
            length = 1,
            columnDefinition = "char"
    )
    private String dataFlag;

    /** 创建时间 */
    @Column(
            name = "create_time"
    )
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /** 修改时间 */
    @Column(
            name = "modify_time"
    )
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTime;

    /**
     * 数据插入前的操作
     */
    @PrePersist
    public void setInsertBefore() {
        this.id = SerialUtils.uuid();
        this.dataFlag = IDict.dataFlag_0;
        this.createTime = new Date();
    }

    /**
     * 数据修改前的操作
     */
    @PreUpdate
    public void setUpdateBefore() {
        this.modifyTime = new Date();
    }

    @Override
    public String toString() {
        return JsonMapper.toJsonString(this);
    }
}
