/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018年4月18日 下午11:20:48
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.common;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.vtobank.database.RuntimeContext;
import com.vtobank.module.domain.BaseService;
import com.vtobank.system.config.FileConfigBean;
import com.vtobank.system.config.FileConfigContext;
import com.vtobank.util.ParseUtils;

/**
 * 通用服务层
 * @author zhangfb
 * @version 1.0.0.1
 */
public class CommonService extends BaseService{
	
	/**
	 * 获取表中图片img_name(主图片),img_paths(其他图片)
	 * @param tabName
	 * @param id
	 * @return
	 */
	public static String[] getImgs(HttpServletRequest req, FileConfigBean config, Long id){
		String sql = "select img_name,img_paths from " + config.getTableName() + " where id= " + id + " and "
				+ getWhereCondition(config.getCondition(), req);
		log.info("获取文件名SQL | "+sql);
		List<Object[]> list = RuntimeContext.getDataSet().queryWithSQL(sql);
		if (list != null && !list.isEmpty()) {
			String mainImg = (String)list.get(0)[0];
			String otherImgs = (String)list.get(0)[1];
			return new String[]{mainImg, otherImgs};
		}
		return null;
		
	}
	
	/**
	 * 获取查询条件
	 * @param configCond
	 * @param req
	 * @return String
	 */
	private static String getWhereCondition(String configCond,HttpServletRequest req){
		configCond = ParseUtils.paramConfig(configCond, req);
		if (StringUtils.isBlank(configCond)) {
			return "1=1";
		}
		return configCond;
	}

	/** 
	 * 更新数据库图片字段
	 * <br> 说明：
	 * <br> 1、上传修改
	 * <br> 2、删除修改
	 * <br> 3、设主修改
	 * <br> 
	 * @param req
	 * @param config
	 * @param id
	 * @param priImg
	 * @param subImg 
	 * @param imgs 原字段值 
	 */
	public static void updateImgField(HttpServletRequest req, FileConfigBean config, Long id, String priImg, String subImg, String[] imgs) {
		log.info("文件上传配置实体="+config+"|{主文件字段值="+priImg+"，原字段值="+imgs[0]+"}|{附文件字段值="+subImg+"，原字段值="+imgs[1]+"}");
		if (StringUtils.isNotBlank(priImg) || StringUtils.isNotBlank(subImg)) {
			StringBuffer sql = new StringBuffer();
			sql.append("update ").append(config.getTableName()).append(" set ");
			boolean flag = false;
			if(StringUtils.isNotBlank(priImg)){
				// 原主字段值
				String pri = (String) imgs[0];
				if (StringUtils.isNotBlank(pri)) {
					if (pri.contains(priImg)) {
						sql.append("img_name=null ");
					} else {
						sql.append("img_name='"+pri+"' ");
					}
				} else {
					sql.append("img_name='"+pri+"' ");
				}
				flag = true;
			} 
			
			if(StringUtils.isNotBlank(subImg)){
				if (flag) {
					sql.append(",");
				}
				// 原附字段值
				String sub = (String) imgs[1];
				if (StringUtils.isNotBlank(sub)) {
					if (sub.contains(subImg)) {
						sub = sub.replace(subImg, "");
						if (sub.endsWith(",")) {
							sub = sub.substring(0, sub.length()-1);
						}
						if (sub.startsWith(",")) {
							sub = sub.substring(1, sub.length());
						}
						sub = sub.replace(",,", ",");
						sql.append("img_paths='"+sub+"' ");
					} else {
						sql.append("img_paths=concat(img_paths,',','"+subImg+"') ");
					}
				} else {
					sql.append("img_paths='"+subImg+"' ");
				}
			}
			sql.append("where id=" + id + " and " + getWhereCondition(config.getCondition(), req));
			log.info("更新sql="+sql.toString());
			RuntimeContext.getDataSet().querySQLUpdate(sql.toString());
		}
	}
	
	public static void main(String[] args) {
		String sub = ",123,234,345,56,,789,";
		String subImg = "234";
		if(StringUtils.isNotBlank(subImg)){
			if (StringUtils.isNotBlank(sub)) {
				if (sub.contains(subImg)) {
					sub = sub.replace(subImg, "");
					if (sub.endsWith(",")) {
						sub = sub.substring(0, sub.length()-1);
					}
					if (sub.startsWith(",")) {
						sub = sub.substring(1, sub.length());
					}
					sub = sub.replace(",,", ",");
				} else {
					sub += "," + subImg; 
				}
			} else {
				sub = subImg; 
			}
		}
		System.out.println(sub);
	}
	
	/**
	 * 获取文件可视路径
	 * @param path
	 * @param req
	 * @return String
	 */
	public static String getViewPath(String path,HttpServletRequest req){
		return FileConfigContext.getFileViewRootPath()+File.separator+ParseUtils.paramConfig(path, req);
	}
}
