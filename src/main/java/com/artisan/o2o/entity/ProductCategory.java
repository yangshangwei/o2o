package com.artisan.o2o.entity;

import java.util.Date;

/**
 * 
 * 
 * @ClassName: ProductCategory
 * 
 * @Description: tb_product_category对应的实体类
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月14日 上午12:26:43
 */
public class ProductCategory {

	private Long productCategoryId;
	/**
	 * 店铺id,表名该产品目录是哪个店铺下的
	 */
	private Long shopId;
	private String productCategoryName;
	private String productCategoryDesc;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;

	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public String getProductCategoryDesc() {
		return productCategoryDesc;
	}

	public void setProductCategoryDesc(String productCategoryDesc) {
		this.productCategoryDesc = productCategoryDesc;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
}
