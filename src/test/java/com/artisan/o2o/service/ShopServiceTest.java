package com.artisan.o2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.dto.ShopExecution;
import com.artisan.o2o.entity.Area;
import com.artisan.o2o.entity.PersonInfo;
import com.artisan.o2o.entity.Shop;
import com.artisan.o2o.entity.ShopCategory;
import com.artisan.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest {

	@Autowired
	ShopService shopService;

	@Test
	public void testAddShop() {
		Shop shop = new Shop();
		PersonInfo personInfo = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();

		personInfo.setUserId(1L);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(1L);

		shop.setOwner(personInfo);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("咖啡店Improve");
		shop.setShopDesc("小工匠的咖啡店Improve");
		shop.setShopAddr("NanJing-Improve");
		shop.setPhone("9876553");
		shop.setPriority(99);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中Improve");
		
		File shopFile = new File("D:/o2o/artisan.jpg");
		
		ShopExecution se = null;
		InputStream ins = null;
		try {
			ins = new FileInputStream(shopFile);
			se = shopService.addShop(shop, ins, shopFile.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		Assert.assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}

	@Test
	public void testGetShopById() {
		Shop shop = shopService.getShopById(30L);
		Assert.assertNotNull(shop);
		Assert.assertEquals("优乐美", shop.getShopName());
		System.out.println(shop.toString());
	}

	@Test
	public void testModifyShop() {
		Shop shop = new Shop();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();

		shop.setShopId(28L);

		area.setAreaId(2);
		shopCategory.setShopCategoryId(2L);

		shop.setArea(area);
		shop.setShopCategory(shopCategory);

		shop.setShopName("Modify咖啡店");
		shop.setShopDesc("Modify小工匠的咖啡店");
		shop.setShopAddr("Modify-NanJing");
		shop.setPhone("123456");
		shop.setPriority(78);

		File shopFile = new File("D:/o2o/artisan.jpg");

		ShopExecution se = null;
		InputStream ins = null;
		try {
			ins = new FileInputStream(shopFile);
			se = shopService.modifyShop(shop, ins, shopFile.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(ShopStateEnum.SUCCESS.getState(), se.getState());
	}


}
