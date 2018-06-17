package com.artisan.o2o.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.ProductCategory;

public class ProductCategoryServiceTest extends BaseTest {

	@Autowired
	private ProductCategoryService productCategoryService;

	@Test
	public void testQueryProductCategoryList() {
		long shopId = 5L;
		List<ProductCategory> productCategories = productCategoryService.queryProductCategoryList(shopId);
		Assert.assertNotNull(productCategories);
		Assert.assertEquals(2, productCategories.size());

		for (ProductCategory productCategory : productCategories) {
			System.out.println(productCategory.toString());
		}
	}

}
