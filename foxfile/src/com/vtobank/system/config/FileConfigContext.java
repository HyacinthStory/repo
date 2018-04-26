/********************************************
 * 功能说明: 文件操作配置类
 * 模块名称: 文件配置模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2017年11月9日 下午6:20:17
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.system.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vtobank.system.constant.ITag;
import com.vtobank.util.ParseUtils;
import com.vtobank.util.PropertiesLoader;

/**
 * 文件操作配置类
 * @author zhangfb
 * @version 1.0.0.1
 */
public class FileConfigContext {
	
	private static final Logger log = Logger.getLogger(FileConfigContext.class);
	
	private static final String FILE_CONFIG_PROPERTIES = "fox-file-config.properties";
	
	/** 公共参数配置 **/
	private static Map<String, String> commonConfig;
	
	/** 文件上传配置参数集合 **/
	private static Map<String, FileConfigBean> configs;
	
	
	/**
	 * 加载文件配置初始化
	 */
	public static void initFileConfig() {
		readFileConfig(FILE_CONFIG_PROPERTIES);
	}
	
	/**
	 * 读取文件配置信息 
	 * @param fileName
	 */
	private static void readFileConfig(String fileName) {
		log.info("=========加载项目文件配置参数开始=========");
		configs=new HashMap<String, FileConfigBean>();
		commonConfig = new HashMap<String, String>();
		try {
			PropertiesLoader loader = new PropertiesLoader(fileName);
			//加载公共参数配置 
			commonConfig.put(ITag.commConfig.FileUploadDir, loader.getProperty(ITag.commConfig.FileUploadDir, ""));
			commonConfig.put(ITag.commConfig.FileViewDir, loader.getProperty(ITag.commConfig.FileViewDir, ""));
			commonConfig.put(ITag.commConfig.FileDownloadDir, loader.getProperty(ITag.commConfig.FileDownloadDir, ""));
			commonConfig.put(ITag.commConfig.UseHttps, loader.getProperty(ITag.commConfig.UseHttps, ""));
			
			//加载图片配置
			String[] imgConfigs = loader.getProperty("imgConfigs","").split(",");
			//加载图片白名单
			String imgWhiteList = loader.getProperty("imgWhiteList","");
			//加载附件配置
			String[] attmConfigs = loader.getProperty("attmConfigs","").split(",");
			//加载附件白名单
			String attWhiteList = loader.getProperty("attWhiteList","").toUpperCase();
			
			//封装图片配置详情
			if (imgConfigs != null) {
				for(String imgConfigName:imgConfigs){
					FileConfigBean config = new FileConfigBean();
					config.setTableName(loader.getProperty("imgConfigs."+imgConfigName+".tableName",""));
					config.setPath(loader.getProperty("imgConfigs."+imgConfigName+".path",""));
					config.setCondition(loader.getProperty("imgConfigs."+imgConfigName+".condition",""));
					config.setColumn(loader.getProperty("imgConfigs."+imgConfigName+".column",""));
					config.setWhiteList(imgWhiteList+imgWhiteList.toUpperCase());
					config.setLimitNum(Integer.valueOf(loader.getProperty("imgConfigs."+imgConfigName+".limitNumber","15")));
					config.setCustomFileName("true".equals((loader.getProperty("imgConfigs."+imgConfigName+".customFileName","false"))));
					configs.put(ITag.fileConfigPrefix.IMG_+imgConfigName, config);
					log.info("图片配置参数："+config.toString());
				}
			}
			
			//封装附件配置详情
			if (attmConfigs != null) {
				for(String attmConfigName:attmConfigs){
					FileConfigBean config=new FileConfigBean();
					config.setTableName(loader.getProperty("attmConfigs."+attmConfigName+".entityName",""));
					config.setPath(loader.getProperty("attmConfigs."+attmConfigName+".path",""));
					config.setWhiteList(attWhiteList+attWhiteList.toUpperCase());
					configs.put(ITag.fileConfigPrefix.ATTM_+attmConfigName, config);
					log.info("附件配置参数："+config.toString());
				}
			}
		} catch (Exception e) {
			log.error("读取文件配置信息，出异常",e);
		}
		log.info("=========加载项目文件配置参数结束=========");
	}
	
	/**
	 * 通过文件类型和文件对象获取配置参数
	 * @param usefor   文件对象
	 * @param fileType 文件类型（01图片 02附件）
	 * @return
	 */
	public static FileConfigBean getFileConfig(String usefor, String fileType){
		if(ITag.fileType.FTImg.equals(fileType)) {  //图片
			return configs.get(ITag.fileConfigPrefix.IMG_+usefor);
		} else if (ITag.fileType.FTAttach.equals(fileType)) {  //附件
			return configs.get(ITag.fileConfigPrefix.ATTM_+usefor);
		} else {
			return null; 
		}
	}
	
	/**
	 * 获取上传文件的根路径
	 * @return
	 */
	public static String getFileUploadRootPath(){
		return commonConfig.get(ITag.commConfig.FileUploadDir);
	}
	
	/**
	 * 获取文件预览的根路径
	 * @return
	 */
	public static String getFileViewRootPath(){
		return commonConfig.get(ITag.commConfig.FileViewDir);
	}
	
	/**
	 * 获取下载文件的根路径
	 * @return
	 */
	public static String getFileDownloadRootPath(){
		return commonConfig.get(ITag.commConfig.FileDownloadDir);
	}
	
	/**
	 * 是否启用https访问服务器
	 * @return
	 */
	public static Boolean useHttps(){
		String useHttps = commonConfig.get(ITag.commConfig.UseHttps);
		return ITag.yesOrNo.Yes.equals(useHttps);
	}
}
