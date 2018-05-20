package com.artisan.o2o.dto;

import java.util.List;

import com.artisan.o2o.entity.Shop;
import com.artisan.o2o.enums.ShopStateEnum;

/**
 * 
 * 
 * @ClassName: ShopExecution
 * 
 * @Description: DTO中还要包含操作商铺的返回结果,单个的实体类无法满足，所以封装到dto中，便于操作
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月19日 下午2:50:29
 */
public class ShopExecution {
	
	/**
	 * 操作结果状态
	 */
	private int  state ;

	/**
	 * 操作结果状态说明
	 */
	private String stateInfo;

	/**
	 * 店铺数量
	 */
	private int count;

	/**
	 * 店铺 （增删改店铺的时候用）
	 */
	private Shop shop;

	/**
	 * 店铺集合 (查询店铺列表的时候用)
	 */
	private List<Shop> shopList;

	
	/**
	 * 
	 * 
	 * @Title:ShopExecution
	 * 
	 * @Description: 构造函数,店铺操作失败的时候使用的构造函数
	 * 
	 * @param shopStateEnum
	 */
	public ShopExecution(ShopStateEnum shopStateEnum) {
		this.state = shopStateEnum.getState();
		this.stateInfo = shopStateEnum.getStateInfo();
	}

	/**
	 * 
	 * 
	 * @Title:ShopExecution
	 * 
	 * @Description:构造函数,店铺操作成功的时候使用的构造函数
	 * 
	 * @param stateEnum
	 * @param shop
	 */
	public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}

	/**
	 * 
	 * 
	 * @Title:ShopExecution
	 * 
	 * @Description:构造函数,店铺操作成功的时候使用的构造函数
	 * 
	 * @param stateEnum
	 * @param shopList
	 */
	public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
	}


	/**
	 * 
	 * setter/getter
	 * 
	 */

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}

}
