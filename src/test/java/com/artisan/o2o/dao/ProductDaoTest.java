package com.artisan.o2o.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.Product;
import com.artisan.o2o.entity.ProductCategory;
import com.artisan.o2o.entity.Shop;

public class ProductDaoTest extends BaseTest {

	@Autowired
	ProductDao productDao;

	@Test
	public void testInsertProdcut() {

		// 注意表中的外键关系，确保这些数据在对应的表中的存在
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(36L);

		// 注意表中的外键关系，确保这些数据在对应的表中的存在
		Shop shop = new Shop();
		shop.setShopId(5L);

		Product product = new Product();
		product.setProductName("test_product");
		product.setProductDesc("product desc");
		product.setImgAddr("/aaa/bbb");
		product.setNormalPrice("10");
		product.setPromotionPrice("8");
		product.setPriority(66);
		product.setCreateTime(new Date());
		product.setLastEditTime(new Date());
		product.setEnableStatus(1);
		product.setProductCategory(productCategory);
		product.setShop(shop);

		int effectNum = productDao.insertProduct(product);
		Assert.assertEquals(1, effectNum);
	}

}
