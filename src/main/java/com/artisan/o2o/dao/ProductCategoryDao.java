package com.artisan.o2o.dao;

import java.util.List;

import com.artisan.o2o.entity.ProductCategory;

public interface ProductCategoryDao {

	List<ProductCategory> selectProductCategoryList(long shopId);
}
