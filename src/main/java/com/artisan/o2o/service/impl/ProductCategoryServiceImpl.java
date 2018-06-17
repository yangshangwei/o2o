package com.artisan.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artisan.o2o.dao.ProductCategoryDao;
import com.artisan.o2o.entity.ProductCategory;
import com.artisan.o2o.service.ProductCategoryService;

/**
 * 
 * 
 * @ClassName: ProductCategoryServiceImpl
 * 
 * @Description: 使用@Service，交由Spring托管
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年6月9日 上午2:46:07
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Override
	public List<ProductCategory> queryProductCategoryList(long shopId) {
		return productCategoryDao.selectProductCategoryList(shopId);
	}

}
