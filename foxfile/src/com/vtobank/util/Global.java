/********************************************
 * 功能说明: 平台全局配置类
 * 模块名称: 平台配置模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉科技技术有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhaorq
 * 开发时间: 2016年12月23日 下午9:15:41
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.DefaultResourceLoader;

import com.vtobank.util.PropertiesLoader;
import com.vtobank.util.StringUtils;

/**
 * 平台全局配置类
 * @author zhangfb
 * @version 1.0.0.1
 */
public class Global {
	
	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = new HashMap<String, String>();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("system.properties");
	
	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";
	
	public static void main(String[] args) {
		System.out.println(Global.getConfig("product.name"));
	}
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取系统配置参数
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			if (value != null) {
				value = value.trim();
				map.put(key, value);
			}
		}
		return value;
	}
	
	public static Integer getInteger(String key) {
		try {
			return Integer.valueOf(getConfig(key));
		} catch(NumberFormatException e) {
			
		}
		return null;
	}
	
	public static Boolean getBoolean(String key) {
		return Boolean.parseBoolean(getConfig(key));
	}
	
    /**
     * 获取工程路径
     * @return
     */
    public static String getProjectPath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)){
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
    }

	public static String getUrlSuffix() {
		// TODO Auto-generated method stub
		return null;
	}

}
