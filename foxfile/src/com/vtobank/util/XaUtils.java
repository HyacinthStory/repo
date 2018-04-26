/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018年4月19日 下午2:31:00
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 对象处理工具类
 * @author zhangfb
 * @version 1.0.0.1
 */
public class XaUtils {

	/**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    @SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object pObj) {
        if (pObj == null)
            return true;
        if (pObj == "")
            return true;
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return true;
            }
        } else if (pObj.getClass().isArray()) {
        	if (Array.getLength(pObj) == 0) {
        		return true;
        	}
        }
        return false;
    }
    
    public static void main(String[] args) {
		String[] aa = {};
		int i = Array.getLength(aa);
		System.out.println(i);
		System.out.println(isEmpty(aa));
	}
}
