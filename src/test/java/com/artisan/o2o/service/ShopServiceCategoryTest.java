package com.artisan.o2o.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.ShopCategory;

public class ShopServiceCategoryTest extends BaseTest {

	@Autowired
	ShopCategoryService shopCategoryService;

	@Test
	public void testQueryShopCategory() {
		
		List<ShopCategory> shopCategories;
		
		// 查询 ShopCategory为null的情况，即查询parent_id is null
		shopCategories = shopCategoryService.getShopCategoryList(null);
		for (ShopCategory shopCategory2 : shopCategories) {
			System.out.println("-----||" + shopCategory2);
		}

		// 查询 ShopCategory不为空的情况
		ShopCategory shopCategory = new ShopCategory();
		shopCategories = shopCategoryService.getShopCategoryList(shopCategory);
		for (ShopCategory shopCategory2 : shopCategories) {
			System.out.println("----->>" + shopCategory2);
		}

		// 查询对应父类下的目录
		ShopCategory parent = new ShopCategory();
		ShopCategory child = new ShopCategory();

		parent.setShopCategoryId(1L);
		child.setParent(parent);

		shopCategories = shopCategoryService.getShopCategoryList(child);
		for (ShopCategory shopCategory2 : shopCategories) {
			System.out.println("-----**" + shopCategory2);
		}

	}
}
