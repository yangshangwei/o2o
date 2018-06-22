package com.artisan.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.artisan.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 
	 * 
	 * @Title: selectProductCategoryList
	 * 
	 * @Description: 根据shopId查询对应的商品目录
	 * 
	 * @param shopId
	 * 
	 * @return: List<ProductCategory>
	 */
	List<ProductCategory> selectProductCategoryList(long shopId);
	
	/**
	 * 
	 * 
	 * @Title: batchInsertProductCategory
	 * 
	 * @Description: 批量增加roductCategory
	 * 
	 * @param productCategoryList
	 * 
	 * @return: int
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);

	
	/**
	 * 
	 * 
	 * @Title: deleteProductCategory
	 * 
	 * @Description: 删除特定shop下的productCategory
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * 
	 * @return: int
	 */
	int deleteProductCategory(@Param("productCategoryId") Long productCategoryId, @Param("shopId") Long shopId);
}
