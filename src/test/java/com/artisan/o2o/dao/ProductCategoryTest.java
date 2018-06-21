package com.artisan.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
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

	@Test
	public void testBatchInsertProductCategory() {

		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryName("ProductCategoryTest1");
		productCategory1.setProductCategoryDesc("ProductCategoryTest1-desc");
		productCategory1.setPriority(300);
		productCategory1.setCreateTime(new Date());
		productCategory1.setLastEditTime(new Date());
		productCategory1.setShopId(5L);

		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("ProductCategoryTest2");
		productCategory2.setProductCategoryDesc("ProductCategoryTest2-desc");
		productCategory2.setPriority(600);
		productCategory2.setCreateTime(new Date());
		productCategory2.setLastEditTime(new Date());
		productCategory2.setShopId(5L);

		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory1);
		productCategoryList.add(productCategory2);

		int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		Assert.assertEquals(2, effectNum);

	}

}
