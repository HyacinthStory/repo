/********************************************
 * 功能说明: 文件工具类
 * 模块名称: 工具包模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2017年11月10日 下午2:29:37
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.vtobank.system.config.FileConfigBean;
import com.vtobank.system.config.FileConfigContext;
import com.vtobank.system.constant.IError;
import com.vtobank.system.constant.ITag;
import com.vtobank.system.constant.UploadResSet;
import com.vtobank.system.exception.BizException;

/**
 * 文件工具类
 * @author zhangfb
 * @version 1.0.0.1
 */
public class FileUtils {

	private static final Logger log = Logger.getLogger(FileUtils.class);
	
	/**
	 * 通用文件上传
	 * @param req
	 * @param resp
	 * @param config
	 * @return 
	 *  {
	 *   	"uploadPriFiles": ("文件1,文件2,文件3......")  --主文件上传名
	 *   	"originPriFiles": ("文件1,文件2,文件3......")  --主文件原始名
	 *   	"uploadSubFiles": ("文件1,文件2,文件3......")  --附文件上传名
	 *   	"originSubFiles": ("文件1,文件2,文件3......")  --附文件原始名
	 *  }
	 * @throws Exception
	 */
	public static Map<UploadResSet, String> upload(HttpServletRequest req,HttpServletResponse resp, FileConfigBean config) throws Exception{
		Map<UploadResSet, String> map = new HashMap<UploadResSet, String>();
		String uploadPriFiles = "";
		String originPriFiles = "";
		String uploadSubFiles = "";
		String originSubFiles = "";
		try {
			// 解决上传的中文文件乱码问题
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html;charset=UTF-8"); 
			// 1.0 检查是否是multipart/form-data类型的  
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if(!isMultipart){
				  throw new BizException(IError.E40002,"the enctype must be multipart/form-data");  
			}
			// 2.0 开始实现上传，创建上传文件需要用到的两个对象  
			DiskFileItemFactory factory = new DiskFileItemFactory(); // 产生FileItem的工厂
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 3.0 创建一个容器来接收解析的内容  
			List<FileItem> items = upload.parseRequest(req);
			// 4.0 遍历,处理解析的内容。
			//获取上传文件目录
			String uploadDir = getFileUploadDir(config.getPath(), req);
			if (StringUtils.isBlank(uploadDir)) {
				return null;
			}
			File dir = new File(uploadDir);
			if(!dir.exists()){
				 dir.mkdirs();
			}
			
			for(FileItem item : items){
				if(!item.isFormField() && StringUtils.isNotEmpty(item.getName())){
					if(item.getFieldName().equals("mainImg")){ //主图片
						uploadPriFiles += "," + processUploadField(item,config,uploadDir);
						originPriFiles += "," + item.getName();
		    		} else { //副图片
		    			uploadSubFiles += "," + processUploadField(item,config,uploadDir);
		    			originSubFiles += "," + item.getName();
		    		}
				}
		    }
			uploadPriFiles = StringUtils.isBlank(uploadPriFiles)?null:uploadPriFiles.substring(1);
			originPriFiles = StringUtils.isBlank(originPriFiles)?null:originPriFiles.substring(1);
			uploadSubFiles = StringUtils.isBlank(uploadSubFiles)?null:uploadSubFiles.substring(1);
			originSubFiles = StringUtils.isBlank(originSubFiles)?null:originSubFiles.substring(1);
		} catch (BizException e) {
			log.error(e.getErrMsg());
			throw new BizException(IError.E40002,e.getErrMsg());
		} catch (UnsupportedEncodingException e) {
			log.error("不支持的编码异常|"+e.getMessage());
			throw new Exception("不支持的编码异常",e);
		} catch (FileUploadException e) {
			log.error("文件上传异常|"+e.getMessage());
			throw new Exception("文件上传异常",e);
		} catch (Exception e) {
			log.error("文件上传失败|"+e.getMessage());
			throw new Exception("文件上传失败",e);
		}
		map.put(UploadResSet.UPLOADPRIFILES, uploadPriFiles);
		map.put(UploadResSet.ORIGINPRIFILES, originPriFiles);
		map.put(UploadResSet.UPLOADSUBFILES, uploadSubFiles);
		map.put(UploadResSet.ORIGINSUBFILES, originSubFiles);
		return map;
	}


	/**
	 * 处理上传字段
	 * @param item
	 * @param config
	 * @param uploadDir
	 * @return fileName
	 * @throws IOException
	 */
	private static String processUploadField(FileItem item,FileConfigBean config,String uploadDir) throws IOException {
		//上传文件的文件名 
        String fileName = item.getName();
        //文件名后缀
        String suffix=item.getName().substring(item.getName().lastIndexOf("."));
        //校验文件格式
        if(!isLegalFileName(config,suffix)){
        	log.error("文件格式不支持"+fileName);
        	throw new BizException(IError.E40001,"文件格式不支持");
        }
        //是否自定义文件名
        if(config.isCustomFileName()){
     		if(ITag.renameType.RTFile.equals(config.getRenameType())){
     			//使用文件原名
     			fileName=fileName.substring(fileName.lastIndexOf("/")+1,fileName.length());
     		}else if(ITag.renameType.RTField.equals(config.getRenameType())){
     			//使用表单域名
     			fileName=item.getFieldName();
     			fileName=fileName.substring(fileName.lastIndexOf("/")+1,fileName.length()).replace(".", "_");
     		}else{
     			fileName=generateFileName(suffix);
        	}
        } else {
        	fileName = generateFileName(suffix); //生成文件名
        }
        BufferedInputStream in = new BufferedInputStream(item.getInputStream());     
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(uploadDir, fileName)));  
        Streams.copy(in, out, true); //true/false:是/否关闭输出流
        log.info("SUCCESS|UPLOAD FILE:"+uploadDir+File.separator+fileName);
        return fileName;
	}
	
	/**
	 * 获取文件上传目录
	 * @param configPath
	 * @return
	 */
	public static String getFileUploadDir(String configPath,HttpServletRequest req){
		configPath = ParseUtils.paramConfig(configPath, req);
		if (StringUtils.isNotBlank(configPath)) {
			return FileConfigContext.getFileUploadRootPath()+File.separator+configPath;
		}
		return null;
	}
	
	/**
	 * 生成文件名：yyMMddHHmmssSSS+三位随机数
	 * @param suffix
	 * @return
	 */
	private static String generateFileName(String suffix){
		DecimalFormat df=new DecimalFormat("000");
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		return sdf.format(new Date()) + df.format(Math.random()*999) + suffix;
	}
	
	/**
	 * 校验文件格式
	 * @param suffix
	 * @return
	 */
	private static Boolean isLegalFileName(FileConfigBean config,String suffix){
		String whiteList = config.getWhiteList();
		return StringUtils.isNotBlank(whiteList) && whiteList.contains(suffix);
	}
}
