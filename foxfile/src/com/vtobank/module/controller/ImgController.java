/********************************************
 * 功能说明: 文件上传方式一
 * 模块名称: 公共模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018年4月18日 下午10:06:17
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.module.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vtobank.module.common.CommonService;
import com.vtobank.module.domain.BaseController;
import com.vtobank.system.config.FileConfigBean;
import com.vtobank.system.config.FileConfigContext;
import com.vtobank.system.constant.ITag;
import com.vtobank.system.constant.UploadResSet;
import com.vtobank.util.FileUtils;
import com.vtobank.util.JsonMapper;
import com.vtobank.util.ParseUtils;

/**
 * 图片上传方式一
 * 	 <br>说明：imgName（主图）和imgPath（其他图）组合
 *   <br>固定参数：usefor,id	
 * @author zhangfb
 * @version 1.0.0.1
 */
@Controller
@RequestMapping("uploadimg")
public class ImgController extends BaseController{
	
	/**
	 * 图片展示
	 * @param req
	 * @param usefor 必须
	 * @param id     必须
	 * @param model
	 * @return String
	 */
	@RequestMapping("page.do")
	public String imgshow(@RequestParam(required=true)String usefor, @RequestParam(required=true)Long id, HttpServletRequest req, Model model) {
		// 获取配置参数
		FileConfigBean config = FileConfigContext.getFileConfig(usefor, ITag.fileType.FTImg);
		log.info("usefor="+usefor+"|id="+id+"|文件配置实体="+JsonMapper.toJsonString(config));
		if (config == null) {
			model.addAttribute("msg", "读取不到配置项！");
			return ITag.errorPage;
		}
		// 获取未传递的必需参数
		String noPushParam = ParseUtils.parseConfigParam(config.getCondition(),req);
		if (StringUtils.isNotBlank(noPushParam)) {
			model.addAttribute("msg", "参数"+noPushParam+"不能为空！");
			return ITag.errorPage;
		}
		// 校验参数
		String[] imgs = CommonService.getImgs(req, config, id);
		if (imgs == null) {
			model.addAttribute("msg", "参数id错误，获取不到数据！");
			return ITag.errorPage;
		}
		// 获取主图 
		String pirImg = imgs[0];
		// 获取附图 
		String subImg = imgs[1];
		String[] subImgs = StringUtils.isBlank(subImg) ? null : subImg.split(",");
		// 获取显示路径
		String path = CommonService.getViewPath(config.getPath(),req);
		model.addAttribute("path", path);
		model.addAttribute("imgname", pirImg);
		model.addAttribute("otherimgs", subImgs);
		model.addAttribute("url_upload", ParseUtils.parseUrl("uploadimg/upload.do?",req));
		model.addAttribute("url_setmain", ParseUtils.parseUrl("uploadimg/setmain.do?",req));
		model.addAttribute("url_delete", ParseUtils.parseUrl("uploadimg/delete.do?",req));
		return "imgload/show";
	}
	
	/**
	 * 图片上传
	 * @param usefor
	 * @param id
	 * @param req
	 * @param model
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping("upload.do")
	public String upload(@RequestParam(required=true)String usefor, @RequestParam(required=true)Long id, HttpServletRequest req,HttpServletResponse resp, Model model) throws Exception {
		// 获取配置参数
		FileConfigBean config = FileConfigContext.getFileConfig(usefor, ITag.fileType.FTImg);
		log.info("usefor="+usefor+"|id="+id+"|文件配置实体="+JsonMapper.toJsonString(config));
		if (config == null) {
			model.addAttribute("msg", "读取不到配置项！");
			return ITag.errorPage;
		}
		// 获取未传递的必需参数
		String noPushParam = ParseUtils.parseConfigParam(config.getCondition(),req);
		if (StringUtils.isNotBlank(noPushParam)) {
			model.addAttribute("msg", "参数"+noPushParam+"不能为空！");
			return ITag.errorPage;
		}
		// 校验参数
		String[] imgs = CommonService.getImgs(req, config, id);
		if (imgs == null) {
			model.addAttribute("msg", "参数错误，获取不到数据！");
			return ITag.errorPage;
		}
		// 文件上传
		Map<UploadResSet, String> upload = FileUtils.upload(req, resp, config);
		// 主图上传名
		String priImg = upload.get(UploadResSet.UPLOADPRIFILES);
		// 附图上传名
		String subImg = upload.get(UploadResSet.UPLOADSUBFILES);
		// 更新字段
		CommonService.updateImgField(req,config,id,priImg,subImg,imgs);
		return imgshow(usefor, id, req, model);
	}
}
