package com.artisan.o2o.service;

import java.io.File;
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
		shop.setShopName("咖啡点");
		shop.setShopDesc("小工匠的咖啡店");
		shop.setShopAddr("NanJing");
		shop.setPhone("9876553");
		shop.setPriority(99);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		
		File shopFile = new File("D:/o2o/artisan.jpg");
		
		ShopExecution se = shopService.addShop(shop, shopFile);
		Assert.assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
}
