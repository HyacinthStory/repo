/********************************************
 * 功能说明: 文件上传结果集
 * 模块名称: 系统模块
 * 系统名称: 互联网金融平台
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018年4月24日 下午11:22:26
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.vtobank.system.constant;

/**
 * 文件上传结果集
 * @author zhangfb
 * @version 1.0.0.1
 */
public enum UploadResSet {
	
	UPLOADPRIFILES("主文件上传名", 1), ORIGINPRIFILES("主文件原始名", 2), UPLOADSUBFILES("附文件上传名", 3), ORIGINSUBFILES("附文件原始名", 4);  
    
	// 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private UploadResSet(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    // 普通方法  
    public static String getName(int index) {  
        for (UploadResSet set : UploadResSet.values()) {  
            if (set.getIndex() == index) {  
                return set.name;  
            }  
        }  
        return null;  
    }  
    // get set 方法  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getIndex() {  
        return index;  
    }  
    public void setIndex(int index) {  
        this.index = index;  
    }  
}
