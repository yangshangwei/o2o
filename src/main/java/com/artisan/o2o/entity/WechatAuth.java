package com.artisan.o2o.entity;

import java.util.Date;

/**
 * 
 * 
 * @ClassName: WechatAuth
 * 
 * @Description: tb_wechat_auth对应的实体类
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月13日 下午10:38:08
 */
public class WechatAuth {

	/**
	 * 主键
	 */
	private Long wechatAuthId;

	/**
	 * Wechat唯一标示
	 */
	private String openId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 关联的用户信息（通过用户id）
	 */
	private PersonInfo personInfo;

	public Long getWechatAuthId() {
		return wechatAuthId;
	}

	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	@Override
	public String toString() {
		return "WechatAuth [wechatAuthId=" + wechatAuthId + ", openId=" + openId + ", createTime=" + createTime + ", personInfo=" + personInfo + "]";
	}

}
