package com.artisan.o2o.dao;

import com.artisan.o2o.entity.Product;

public interface ProductDao {

	/**
	 * 
	 * 
	 * @Title: insertProduct
	 * 
	 * @Description: 增加商品
	 * 
	 * @param product
	 * 
	 * @return: int
	 */
	int insertProduct(Product product);
}
