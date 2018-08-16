package com.artisan.o2o.service;

import java.util.List;

import com.artisan.o2o.entity.ShopCategory;

public interface ShopCategoryService {

	// redis key的前缀，抽取到接口层，方便使用
	public static final String SCLISTKEY = "shopcategory";

	List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);
}
