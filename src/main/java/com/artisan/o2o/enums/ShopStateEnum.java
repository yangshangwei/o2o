package com.artisan.o2o.enums;

/**
 * 
 * 
 * @ClassName: ShopStateEnum
 * 
 * @Description: 使用枚举表述常量数据字典
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月19日 下午3:00:31
 */
public enum ShopStateEnum {

	CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"), PASS(2, "审核通过"), INNER_ERROR(-1001, "操作失败"), NULL_SHOPID(-1002, "ShopId为空"), NULL_SHOP_INFO(-1003, "传入了空的信息");
	
	private int state;
	private String stateInfo;

	/**
	 * 
	 * 
	 * @Title:ShopStateEnum
	 * 
	 * @Description:私有构造函数,禁止外部初始化改变定义的常量
	 * 
	 * @param state
	 * @param stateInfo
	 */
	private ShopStateEnum(int state, String stateInfo) {
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
	 * 
	 * @param state
	 * 
	 * @return: ShopStateEnum
	 */
	public static ShopStateEnum stateOf(int state) {
		for (ShopStateEnum stateEnum : values()) {
			if(stateEnum.getState() == state){
				return stateEnum;
			}
		}
		return null;
	}

}
