/********************************************
 * 功能说明: 配置文件加载类
 * 模块名称: 平台工具模块
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

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * 配置文件加载类
 * @author zhaorq
 * @version 1.0.0.1
 */
public class PropertiesLoader {
	
	private final Properties properties;
	
	public PropertiesLoader(String... resourcesPaths) {
		properties = loadProperties(resourcesPaths);
	}

	public Properties getProperties() {
		return properties;
	}

	/**
	 * 取出系统参数
	 */
	private String getValue(String key) {
		String systemProperty = System.getProperty(key);
		if (systemProperty != null) {
			return systemProperty;
		}
		if (properties.containsKey(key)) {
	        return properties.getProperty(key);
	    }
	    return null;
	}

	/**
	 * 取出配置参数
	 */
	public String getProperty(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return value;
	}

	/**
	 * 取出配置参数
	 */
	public String getProperty(String key, String defaultValue) {
		String value = getValue(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * 载入多个配置文件
	 */
	private Properties loadProperties(String... resourcesPaths) {
		Properties props = new Properties();
		ClassLoader ccl = Thread.currentThread().getContextClassLoader();
		for (String location : resourcesPaths) {
			InputStream is = null;
			try {
				is = ccl.getResourceAsStream(location);
				props.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
		            if (is != null) {
		            	is.close();
		            }
		        } catch (IOException ioe) {
		        	ioe.printStackTrace();
		        }
			}
		}
		return props;
	}

}
