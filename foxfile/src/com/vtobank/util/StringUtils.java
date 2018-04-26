/********************************************
 * 功能说明: 字符串工具类
 * 模块名称: 通用工具模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2017年5月4日 下午3:11:09
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.util;

import org.apache.commons.lang3.BooleanUtils;

/**
 * 字符串工具类
 * @author zhangfb
 * @verion 1.0.0.1
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	/**
	 * 如果对象为空，则使用defaultVal值 
	 * 	see: ObjectUtils.toString(obj, defaultVal)
	 * @param obj
	 * @param defaultVal
	 * @return
	 */
	public static String toString(Object obj, String defaultVal) {
		return obj == null ? defaultVal : obj.toString();
	}

	/**
	 * 转换为Boolean类型
	 * 'true', 'on', 'y', 't', 'yes' or '1' (case insensitive) will return true. Otherwise, false is returned.
	 */
	public static boolean toBoolean(Object val) {
		if (val == null){
			return false;
		}
		return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
	}

}
