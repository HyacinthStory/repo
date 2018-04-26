/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018年4月25日 下午10:46:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 解析url
 * @author zhangfb
 * @version 1.0.0.1
 */
public class ParseUtils {

	/**
	 * 解析请求参数
	 * @param req
	 * @return String
	 */
	public static String parseUrl(String prefix,HttpServletRequest req){
		StringBuffer sb=new StringBuffer();
		for(Object k:req.getParameterMap().keySet()){
			sb.append("&").append(k.toString()).append("=").append(req.getParameter(k.toString()));
		}
		String url = prefix + sb.substring(1);
		return url;
	}
	
	/**
	 * 解析条件参数
	 */
	public static String paramConfig(String configParam,HttpServletRequest req) {
		if(StringUtils.isNotBlank(configParam)){
			for(Object k:req.getParameterMap().keySet()){
				String key="#["+k+"]";
				configParam=configParam.replace(key, req.getParameter(k.toString()));
			}
			return configParam;
		}
		return null;
	}
	
	/**
	 * 解析未传递的配置参数
	 * @param configParam
	 * @return String
	 */
	public static String parseConfigParam(String configParam,HttpServletRequest req){
		if (StringUtils.isNotBlank(configParam)) {
			for(Object k:req.getParameterMap().keySet()){
				String key="#["+k+"]";
				configParam=configParam.replace(key, req.getParameter(k.toString()));
			}
			StringBuilder noCheckField = new StringBuilder();
			for (int i=0;i<configParam.length();i++) {
				char c = configParam.charAt(i);
				int j=1;
				if ("[".equals(String.valueOf(c))) {
					String str = "";
					while (!"]".equals(String.valueOf(configParam.charAt(i+j)))) {
						str += configParam.charAt(i+j);
						j++;
					}
					noCheckField.append(",").append(str);
				}
			}
			if (StringUtils.isBlank(noCheckField.toString())) {
				return null;
			}
			return noCheckField.substring(1);
		}
		return null;
	}
	
	public static void main(String[] args) {
		StringBuilder noCheckField = new StringBuilder();
		System.out.println(StringUtils.isBlank(noCheckField.toString()));
	}
}
