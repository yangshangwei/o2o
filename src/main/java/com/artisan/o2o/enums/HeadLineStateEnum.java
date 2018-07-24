package com.artisan.o2o.enums;

public enum HeadLineStateEnum {
	
	SUCCESS(1, "操作成功"), INNER_ERROR(-1001, "操作失败");

	private int state;
	private String stateInfo;

	/**
	 * 
	 * 
	 * @Title:HeadLineStateEnum
	 * 
	 * @Description:私有构造函数,禁止外部初始化改变定义的常量
	 * 
	 * @param state
	 * @param stateInfo
	 */
	private HeadLineStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	/**
	 * 
	 * 
	 * @Title: getState
	 * 
	 * @Description: 仅设置get方法,禁用set
	 * 
	 * @return
	 * 
	 * @return: int
	 */
	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	/**
	 * 
	 * 
	 * @Title: stateOf
	 * 
	 * @Description: 定义换成pulic static 暴漏给外部,通过state获取HeadLineStateEnum
	 * 
	 *               values()获取全部的enum常量
	 * 
	 * @param state
	 * 
	 * @return: ShopStateEnum
	 */
	public static HeadLineStateEnum stateOf(int state) {
		for (HeadLineStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}
}
