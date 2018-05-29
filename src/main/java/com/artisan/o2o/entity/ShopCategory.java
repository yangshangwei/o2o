package com.artisan.o2o.entity;

import java.util.Date;

/**
 * 
 * 
 * @ClassName: ShopCategory
 * 
 * @Description: tb_shop_category对应的实体类
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月13日 下午11:26:19
 */
public class ShopCategory {
	/**
	 * 主键
	 */
	private Long shopCategoryId;

	/**
	 * 店铺目录名称
	 */
	private String shopCategoryName;

	/**
	 * 店铺目录描述
	 */
	private String shopCategoryDesc;

	/**
	 * 店铺目录对应的图片地址
	 */
	private String shopCategoryImg;

	/**
	 * 权重,值越大，越优先展示
	 */
	private Integer priority;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date lastEditTime;

	/**
	 * 父类,主要是获取ID，用于店铺目录的分层展示
	 */
	private ShopCategory parent;

	public Long getShopCategoryId() {
		return shopCategoryId;
	}

	public void setShopCategoryId(Long shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}

	public String getShopCategoryName() {
		return shopCategoryName;
	}

	public void setShopCategoryName(String shopCategoryName) {
		this.shopCategoryName = shopCategoryName;
	}

	public String getShopCategoryDesc() {
		return shopCategoryDesc;
	}

	public void setShopCategoryDesc(String shopCategoryDesc) {
		this.shopCategoryDesc = shopCategoryDesc;
	}

	public String getShopCategoryImg() {
		return shopCategoryImg;
	}

	public void setShopCategoryImg(String shopCategoryImg) {
		this.shopCategoryImg = shopCategoryImg;
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


	public ShopCategory getParent() {
		return parent;
	}

	public void setParent(ShopCategory parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "ShopCategory [shopCategoryId=" + shopCategoryId + ", shopCategoryName=" + shopCategoryName + ", shopCategoryDesc=" + shopCategoryDesc + ", shopCategoryImg=" + shopCategoryImg
				+ ", priority=" + priority + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + ", parent=" + parent + "]";
	}



}
