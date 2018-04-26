/**
 * @author   zhangyp
 * @created  2013-12-7 下午6:28:53
 * @email    zviolet@163.com
 * TODO      类描述
 */
package com.vtobank.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClassDesc {
	
	public String tableName() default "";
	
	public String desc();
	

}
