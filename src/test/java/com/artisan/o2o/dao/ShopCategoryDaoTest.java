package com.artisan.o2o.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest {
	

	@Autowired
	ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testQueryShopCategoryList() {

		// shopCategoryCondition 不为null的情况，查询parent_id is not null 的数据
		ShopCategory shopCategory = new ShopCategory();
		List<ShopCategory> categoryList = shopCategoryDao.queryShopCategoryList(shopCategory);
		Assert.assertEquals(2, categoryList.size());
		for (ShopCategory shopCategory2 : categoryList) {
			System.out.println(shopCategory2);
		}

		// shopCategoryCondition.parent 不为null的情况

		// 查询parent=1的店铺目录
		ShopCategory child = new ShopCategory();
		ShopCategory parent = new ShopCategory();
		parent.setShopCategoryId(1L);
		child.setParent(parent);

		categoryList = shopCategoryDao.queryShopCategoryList(child);
		Assert.assertEquals(2, categoryList.size());
		for (ShopCategory shopCategory2 : categoryList) {
			System.out.println(shopCategory2);
		}

		// 查询 parent is null 的情况
		categoryList = shopCategoryDao.queryShopCategoryList(null);
		Assert.assertEquals(1, categoryList.size());
		System.out.println(categoryList.get(0));
	}


}
