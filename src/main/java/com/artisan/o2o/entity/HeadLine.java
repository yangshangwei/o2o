package com.artisan.o2o.entity;

import java.util.Date;

/**
 * 
 * 
 * @ClassName: HeadLine
 * 
 * @Description: tb_head_line对应的实体类
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月13日 下午11:01:57
 */
public class HeadLine {
	/**
	 * 
	 */
	private Long lineId;

	/**
	 * 头条名
	 */
	private String lineName;

	/**
	 * 头条链接
	 */
	private String lineLink;

	/**
	 * 图片地址
	 */
	private String lineImg;

	/**
	 * 权重，数值越大，优先展示
	 */
	private Integer priority;

	/**
	 * 状态 0 不可用 1 可用
	 */
	private Integer enableStatus;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date lastEditTime;

	public Long getLineId() {
		return lineId;
	}

	public void setLineId(Long lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getLineLink() {
		return lineLink;
	}

	public void setLineLink(String lineLink) {
		this.lineLink = lineLink;
	}

	public String getLineImg() {
		return lineImg;
	}

	public void setLineImg(String lineImg) {
		this.lineImg = lineImg;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
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
		return "HeadLine [lineId=" + lineId + ", lineName=" + lineName + ", lineLink=" + lineLink + ", lineImg=" + lineImg + ", priority=" + priority + ", enableStatus=" + enableStatus
				+ ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + "]";
	}

}
