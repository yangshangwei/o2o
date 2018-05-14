package com.artisan.o2o.entity;

import java.util.Date;

/**
 * 
 * 
 * @ClassName: LocalAuth
 * 
 * @Description: tb_local_auth对应的实体类
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月13日 下午10:41:21
 */
public class LocalAuth {
	/**
	 * 主键
	 */
	private Integer localAuthId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date lastEditTime;

	/**
	 * 关联的用户信息（通过用户id）
	 */
	private PersonInfo personInfo;

	public Integer getLocalAuthId() {
		return localAuthId;
	}

	public void setLocalAuthId(Integer localAuthId) {
		this.localAuthId = localAuthId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	@Override
	public String toString() {
		return "LocalAuth [localAuthId=" + localAuthId + ", userName=" + userName + ", password=" + password + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + ", personInfo="
				+ personInfo + "]";
	}

}
