package com.artisan.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.ProductCategory;


public class ProductCategoryTest extends BaseTest {
	
	@Autowired
	ProductCategoryDao productCategoryDao;
	
	@Test
	public void testSelectProductCategoryList() {
		long shopId = 5L;
		List<ProductCategory> productCategories = productCategoryDao.selectProductCategoryList(shopId);
		// shopId = 5 有2条测试数据,期望list中有2条
		assertEquals(2, productCategories.size());
		// SQL中按照权重排序, product1 priority 99 ,期望第一条数据是 product1
		assertEquals("product1", productCategories.get(0).getProductCategoryName());

		for (ProductCategory productCategory : productCategories) {
			System.out.println(productCategory.toString());
		}

		productCategories = productCategoryDao.selectProductCategoryList(6L);
		assertEquals(0, productCategories.size());

	}
}
