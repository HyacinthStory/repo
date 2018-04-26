/**
 * @author   zhangyp
 * @created  2013-12-9 下午7:56:23
 * @email    zviolet@163.com
 * TODO
 */
package com.vtobank.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodDesc {
	
	//目的
	public String target() default "";

}
