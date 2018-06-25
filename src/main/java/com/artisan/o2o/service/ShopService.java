package com.artisan.o2o.service;

import com.artisan.o2o.dto.ImageHolder;
import com.artisan.o2o.dto.ShopExecution;
import com.artisan.o2o.entity.Shop;
import com.artisan.o2o.exception.ShopOperationException;

public interface ShopService {
	

	/**
	 * 
	 * 
	 * @Title: addShop
	 * 
	 * @Description: 重构后的方法，将shopFileInputStream和fileName封装到ImageHolder类中
	 * 
	 *               重构之前的入参为：Shop shop, InputStream shopFileInputStream, String
	 *               fileName
	 * 
	 *               因为在处理添加商品的时候，既需要处理商品的缩略图 ，又需要处理多个商品详情图片，如果还是直接传inputstream
	 *               和 filename的话 就需要5个参数，不方便调用。
	 * 
	 *               所里这里选择将inputstream 和 filename封装到ImageHolder中，方便调用。
	 * 
	 *               所里这里也需要重构
	 * 
	 * @param shop
	 * @param imageHolder
	 * @return
	 * @throws ShopOperationException
	 * 
	 * @return: ShopExecution
	 */
	ShopExecution addShop(Shop shop, ImageHolder imageHolder) throws ShopOperationException;

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
	ShopExecution modifyShop(Shop shop, ImageHolder imageHolder) throws ShopOperationException;
	
	
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
