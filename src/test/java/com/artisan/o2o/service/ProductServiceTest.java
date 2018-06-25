package com.artisan.o2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.dto.ImageHolder;
import com.artisan.o2o.dto.ProductExecution;
import com.artisan.o2o.entity.Product;
import com.artisan.o2o.entity.ProductCategory;
import com.artisan.o2o.entity.Shop;
import com.artisan.o2o.enums.ProductStateEnum;

public class ProductServiceTest extends BaseTest {

	@Autowired
	private ProductService productService;

	@Test
	public void testAddProduct() throws Exception {

		// 注意表中的外键关系，确保这些数据在对应的表中的存在
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(36L);

		// 注意表中的外键关系，确保这些数据在对应的表中的存在
		Shop shop = new Shop();
		shop.setShopId(5L);

		// 构造Product
		Product product = new Product();
		product.setProductName("test_product");
		product.setProductDesc("product desc");

		product.setNormalPrice("10");
		product.setPromotionPrice("8");
		product.setPriority(66);
		product.setCreateTime(new Date());
		product.setLastEditTime(new Date());
		product.setEnableStatus(1);
		product.setProductCategory(productCategory);
		product.setShop(shop);

		// 构造 商品图片
		File productFile = new File("D:/o2o/artisan.jpg");
		InputStream ins = new FileInputStream(productFile);
		ImageHolder imageHolder = new ImageHolder(ins, productFile.getName());

		// 构造商品详情图片
		List<ImageHolder> prodImgDetailList = new ArrayList<ImageHolder>();

		File productDetailFile1 = new File("D:/o2o/1.jpg");
		InputStream ins1 = new FileInputStream(productDetailFile1);
		ImageHolder imageHolder1 = new ImageHolder(ins1, productDetailFile1.getName());

		File productDetailFile2 = new File("D:/o2o/2.jpg");
		InputStream ins2 = new FileInputStream(productDetailFile2);
		ImageHolder imageHolder2 = new ImageHolder(ins2, productDetailFile2.getName());

		prodImgDetailList.add(imageHolder1);
		prodImgDetailList.add(imageHolder2);

		// 调用服务
		ProductExecution pe = productService.addProduct(product, imageHolder, prodImgDetailList);
		Assert.assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
}
