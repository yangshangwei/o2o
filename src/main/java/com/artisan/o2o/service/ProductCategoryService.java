package com.artisan.o2o.service;

import java.util.List;

import com.artisan.o2o.entity.ProductCategory;

public interface ProductCategoryService {

	List<ProductCategory> queryProductCategoryList(long shopId);

}
