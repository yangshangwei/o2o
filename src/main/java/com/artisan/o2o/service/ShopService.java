package com.artisan.o2o.service;

import java.io.InputStream;

import com.artisan.o2o.dto.ShopExecution;
import com.artisan.o2o.entity.Shop;
import com.artisan.o2o.exception.ShopOperationException;

public interface ShopService {
	
	/**
	 * 
	 * 
	 * @Title: addShop
	 * 
	 * @Description: 新增商铺
	 * 
	 * @param shop
	 * @param shopFileInputStream
	 * @param fileName
	 * @return
	 * 
	 * @return: ShopExecution
	 */
	ShopExecution addShop(Shop shop, InputStream shopFileInputStream, String fileName) throws ShopOperationException;
	
	/**
	 * 
	 * 
	 * @Title: getShopById
	 * 
	 * @Description: 根据shopId查询商铺
	 * 
	 * @param shopId
	 * @return
	 * 
	 * @return: Shop
	 */
	Shop getShopById(long shopId);

	/**
	 * 
	 * 
	 * @Title: modifyShop
	 * 
	 * @Description: 编辑商铺信息
	 * 
	 * @param shop
	 * @param shopFileInputStream
	 * @param fileName
	 * @return
	 * 
	 * @return: ShopExecution
	 */
	ShopExecution modifyShop(Shop shop, InputStream shopFileInputStream, String fileName) throws ShopOperationException;
}
