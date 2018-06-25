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
import com.artisan.o2o.dto.ImageHolder;
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
			ImageHolder imageHolder = new ImageHolder(ins, shopFile.getName());
			se = shopService.addShop(shop, imageHolder);
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
			ImageHolder imageHolder = new ImageHolder(ins, shopFile.getName());
			se = shopService.modifyShop(shop, imageHolder);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(ShopStateEnum.SUCCESS.getState(), se.getState());
	}

	@Test
	public void testGetShopList() {

		Shop shopCondition = new Shop();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);

		shopCondition.setOwner(personInfo);
		shopCondition.setShopName("咖啡");

		// 符合 shop_name like '%咖啡%' 且 owner_id =1 有3条数据，

		// 第二个参数 和 第三个参数 从pageIndex=1 第一页取数据，取2条 pageSize=2
		ShopExecution se = shopService.getShopList(shopCondition, 1, 2);
		// 按照tb_shop中的数据筛选 符合条件的数据3条， 从第一页开始取2条，se.getShopList().size() 应该有2条数据，
		Assert.assertNotNull(se);
		Assert.assertEquals(2, se.getShopList().size());
		Assert.assertEquals(3, se.getCount());

		// 按照tb_shop中的数据筛选 符合条件的数据3条， 从第2页开始取2条，se.getShopList().size()
		// 应该只有1条数据，总数仍为3
		se = shopService.getShopList(shopCondition, 2, 2);
		Assert.assertNotNull(se);
		Assert.assertEquals(1, se.getShopList().size());
		Assert.assertEquals(3, se.getCount());
	}

}
