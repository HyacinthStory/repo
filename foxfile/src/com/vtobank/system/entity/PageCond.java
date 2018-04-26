/**
 * @author   zhangyp
 * @created  2013-12-28 下午7:39:59
 * @email    zviolet@163.com
 * TODO
 */
package com.vtobank.system.entity;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 分页对象 
 */
public class PageCond {
	
	//分页查询条件，返回记录的起始记录号
	private Integer begin = 0;
	//分页查询条件，返回记录数长度
	private Integer length = 10;
	//分页状态信息，总记录数
	private Integer count = 0;
	//分页状态信息，当前页号
	private Integer currentPage = 0;
	//分页状态信息，当前页记录数
	private Integer size = 0;
	
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public static PageCond getPage(HttpServletRequest req){
		String begin=req.getParameter("begin");
		String length=req.getParameter("length");
		
		PageCond pageCond=new PageCond();
		try {
			pageCond.setBegin(Integer.parseInt(begin));
		} catch (Exception e) {
			pageCond.setBegin(0);
		}
		try {
			pageCond.setLength(Integer.parseInt(length));
		} catch (Exception e) {
			pageCond.setLength(10);
		}
		pageCond.setCurrentPage(pageCond.begin/pageCond.length);
		return pageCond;
	}
	
	public static PageCond getPage(String pageNumStr,String pageSizeStr){
		PageCond pageCond = new PageCond();
		if (StringUtils.isBlank(pageSizeStr)) {
			pageSizeStr = "10";
		}
		try {
			Integer pageSize=Integer.parseInt(pageSizeStr);
			pageCond.setLength(pageSize);
		} catch (Exception e) {
			pageCond.setLength(10);
		}
		try {
			Integer pageNum = Integer.parseInt(pageNumStr);
			pageCond.setBegin((pageNum-1)*pageCond.getLength());
		} catch (Exception e) {
			pageCond.setBegin(0);
		}
		pageCond.setCurrentPage(pageCond.begin / pageCond.length);
		return pageCond;
	}

}
