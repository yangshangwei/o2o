package com.artisan.o2o.service;

import java.util.List;

import com.artisan.o2o.dto.ProductCategoryExecution;
import com.artisan.o2o.entity.ProductCategory;
import com.artisan.o2o.exception.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * 
	 * 
	 * @Title: queryProductCategoryList
	 * 
	 * @Description: 根据shopId查询ProductCategory
	 * 
	 * @param shopId
	 * 
	 * @return: List<ProductCategory>
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);

	/**
	 * 
	 * 
	 * @Title: addProductCategory
	 * 
	 * @Description: 批量插入ProductCategory
	 * 
	 * @param productCategoryList
	 * @throws ProductCategoryOperationException
	 * 
	 * @return: ProductCategoryExecution
	 */
	ProductCategoryExecution addProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

	/**
	 * 
	 * 
	 * @Title: deleteProductCategory
	 * 
	 * @Description: 需要先将该商品目录下的商品的类别Id置为空，然后再删除该商品目录， 因此需要事务控制
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @throws ProductCategoryOperationException
	 * 
	 * @return: ProductCategoryExecution
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
