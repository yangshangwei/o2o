package com.artisan.o2o.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.dto.ProductCategoryExecution;
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

	@Test
	public void testAddProductCategory() {
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryName("ProductCategoryTest3");
		productCategory1.setProductCategoryDesc("ProductCategoryTest3-desc");
		productCategory1.setPriority(300);
		productCategory1.setCreateTime(new Date());
		productCategory1.setLastEditTime(new Date());
		productCategory1.setShopId(5L);

		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("ProductCategoryTest4");
		productCategory2.setProductCategoryDesc("ProductCategoryTest4-desc");
		productCategory2.setPriority(600);
		productCategory2.setCreateTime(new Date());
		productCategory2.setLastEditTime(new Date());
		productCategory2.setShopId(5L);

		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory1);
		productCategoryList.add(productCategory2);

		ProductCategoryExecution productCategoryExecution = productCategoryService.addProductCategory(productCategoryList);

		Assert.assertEquals(1, productCategoryExecution.getState());
		Assert.assertEquals(2, productCategoryExecution.getProductCategoryList().size());
	}

	@Test
	public void testDeleteProductCategory() {

		ProductCategoryExecution productCategoryExecution = productCategoryService.deleteProductCategory(26, 5);
		Assert.assertEquals(1, productCategoryExecution.getState());
		ProductCategoryExecution productCategoryExecution2 = productCategoryService.deleteProductCategory(27, 5);
		Assert.assertEquals(1, productCategoryExecution2.getState());


	}
}
