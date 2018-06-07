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
	
	
	/**
	 * 
	 * 
	 * @Title: getShopList
	 * 
	 * @Description: 获取商铺列表. 在这一个方法中同样的会调用查询总数的DAO层方法，封装到ShopExecution中
	 * 
	 * @param shopCondition
	 * @param pageIndex
	 *            前端页面 只有第几页 第几页 定义为pageIndex
	 * @param pageSize
	 *            展示的行数
	 * @throws ShopOperationException
	 * 
	 * @return: ShopExecution
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) throws ShopOperationException;;
	
}
