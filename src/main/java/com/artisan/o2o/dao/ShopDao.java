package com.artisan.o2o.dao;

import com.artisan.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 
	 * 
	 * @Title: insertShop
	 * 
	 * @Description: 店铺注册
	 * 
	 * @param shop
	 * 
	 * @return: int 受影响的行数 1即为成功 -1(mybtis返回的值)失败
	 */
	int insertShop(Shop shop);

	/**
	 * 
	 * 
	 * @Title: updateShop
	 * 
	 * @Description: 更新店铺
	 * 
	 * @param shop
	 * 
	 * @return: int
	 */
	int updateShop(Shop shop);

}
