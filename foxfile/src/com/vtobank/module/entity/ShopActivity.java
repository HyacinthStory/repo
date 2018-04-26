package com.vtobank.module.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.vtobank.module.domain.BaseEntity;
import com.vtobank.util.JsonMapper;

/**
 * 商户促销表
 */
public class ShopActivity extends BaseEntity{

	// @Fields serialVersionUID : 根据类名、方法、变量等生成的hash值 
	private static final long serialVersionUID = -6122881992374120034L;
	
	private Long shopId;
	private String shopCode;
	private String shopName; //商户名称
	private Long goodsId;
	private String goodsName;
	private Double discount;  //折扣
	private BigDecimal discountPrice; //折扣价格
	private Date discountStartDate; //折扣开始时间
	private Date discountEndDate; //折扣结束时间
	private BigDecimal price; //单价
	private Integer score; //积分
	private BigDecimal salePrice; //销售价格
	private Integer saleScore; //销售积分
	private String currentStatus; //状态
    private String activityPhotos; //商户活动图片
    private String activitySummary; //商户活动描述
    private Date createTime; //创建时间
    
    public Date getCreateTime(){
        return createTime;
    }
    
    public void setCreateTime(Date createTime){
        this.createTime= createTime;
    }

    public String getActivityPhotos(){
        return activityPhotos;
    }
    
    public void setActivityPhotos(String activityPhotos){
        this.activityPhotos= activityPhotos;
    }
    
    public String getActivitySummary(){
        return activitySummary;
    }
    
    public void setActivitySummary(String activitySummary){
        this.activitySummary= activitySummary;
    }
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getShopCode() {
		return shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Date getDiscountStartDate() {
		return discountStartDate;
	}
	public void setDiscountStartDate(Date discountStartDate) {
		this.discountStartDate = discountStartDate;
	}
	public Date getDiscountEndDate() {
		return discountEndDate;
	}
	public void setDiscountEndDate(Date discountEndDate) {
		this.discountEndDate = discountEndDate;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getSaleScore() {
		return saleScore;
	}
	public void setSaleScore(Integer saleScore) {
		this.saleScore = saleScore;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	
	@Override
	public String toString() {
		return JsonMapper.toJsonString(this);
	}
}
