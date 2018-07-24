package com.artisan.o2o.enums;

public enum ShopCategoryStateEnum {
	
	SUCCESS(1, "操作成功"), INNER_ERRO(-1001, "操作失败");

	private int state ;
	private String stateInfo; 
	
	/**
	 * 
	 * 
	 * @Title:ShopCategoryStateEnum
	 * 
	 * @Description:私有构造函数,禁止外部初始化改变定义的常量
	 * 
	 * @param state
	 * @param stateInfo
	 */
	private ShopCategoryStateEnum(int state , String stateInfo){
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
	 * @Description: 定义换成pulic static 暴漏给外部,通过state获取ShopStateEnum
	 * 
	 *               values()获取全部的enum常量
	 * @param state
	 * 
	 * @return: ShopCategoryStateEnum
	 */
	public static ShopCategoryStateEnum stateOf(int state) {
		for (ShopCategoryStateEnum shopCategoryStateEnum : values()) {
			if (shopCategoryStateEnum.getState() == state) {
				return shopCategoryStateEnum;
			}
		}
		return null;
	}
}
