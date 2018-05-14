package com.artisan.o2o.entity;

import java.util.Date;

/**
 * 
 * 
 * @ClassName: Area
 * 
 * @Description: 区域实体列,对应数据库中的tb_area.属性采用引用类型(Integer\Long等),
 *               不建议使用基本类型(基本类型有默认值) ,以免mybatis动态sql出现莫名其妙的问题
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月13日 下午5:15:39
 */
public class Area {

	/**
	 * 区域Id
	 */
	private Integer areaId;


	/**
	 * 区域名称
	 */
	private String areaName;

	/**
	 * 权重,数值越大页面展示越靠前
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


	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", areaName=" + areaName + ", priority=" + priority + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}

}
