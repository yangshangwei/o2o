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

	/**
	 * 
	 * 
	 * @Title: selectProductById
	 * 
	 * @Description: 根据productId查询product
	 * 
	 * @param productId
	 * 
	 * @return: Product
	 */
	Product selectProductById(long productId);

	/**
	 * 
	 * 
	 * @Title: updateProduct
	 * 
	 * @Description: 修改商品
	 * 
	 * @param product
	 * 
	 * @return: int
	 */
	int updateProduct(Product product);

}
