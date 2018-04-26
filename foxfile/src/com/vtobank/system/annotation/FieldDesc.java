/**
 * @author   zhangyp
 * @created  2013-12-7 下午5:05:43
 * @email    zviolet@163.com
 * TODO      类的字段描述
 */
package com.vtobank.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldDesc {
	
	//java类型
	public String javaType() default "";
	//sql类型
	public String sqlType() default "";
	//含义
	public String desc() default "";
	//值
	public String value() default "";
	//关联表
	public String refTable() default "";
	//关联字段
	public String refField() default "";
	//关联实体
	public String refEntity() default "";
	//关联属性
	public String refProperty() default "";

}
