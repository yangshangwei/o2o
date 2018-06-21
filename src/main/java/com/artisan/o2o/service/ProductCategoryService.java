package com.artisan.o2o.service;

import java.util.List;

import com.artisan.o2o.entity.ProductCategory;

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

}
