package com.artisan.o2o.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.Area;
import com.artisan.o2o.entity.PersonInfo;
import com.artisan.o2o.entity.Shop;
import com.artisan.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopDaoTest.class);

	@Autowired
	ShopDao shopDao;
	
	@Test
	@Ignore
	public void testQueryArea() {

		Shop shop = new Shop();
		PersonInfo personInfo = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();

		// 因为tb_shop表中有外键约束,因此务必确保 设置的这几个id在对应的表中存在.
		// 我们提前在tb_person_inf tb_area
		// tb_shop_category分别添加了如下id的数据,以避免插入tb_shop时抛出如下异常
		// com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException:
		// Cannot add or update a child row: a foreign key constraint fails
		// (`o2o`.`tb_shop`, CONSTRAINT `fk_shop_area` FOREIGN KEY (`area_id`)
		// REFERENCES `tb_area` (`area_id`))
		personInfo.setUserId(1L);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(1L);

		shop.setOwner(personInfo);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("Artisan");
		shop.setShopDesc("ArtisanDesc");
		shop.setShopAddr("NanJing");
		shop.setPhone("123456");
		shop.setShopImg("/xxx/xxx");
		shop.setPriority(99);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("Waring");
		
		int effectNum = shopDao.insertShop(shop);

		Assert.assertEquals(effectNum, 1);
		logger.debug("insert  successfully");
	}

	@Test
	@Ignore
	public void testUpdateShop() {
		// shop_id 不可更新 personInfo不可更新
		Shop shop = new Shop();

		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();

		// 模拟更新 shop_id=5的记录 。 因为目前数据库中只有一条shop_id=5的数据
		shop.setShopId(5L);

		// 将area_id更新成2
		area.setAreaId(2);
		// 为了防止因外键约束，导致更新异常，同时也能验证更新方法没有问题
		// 新增一条测试数据将shopCategoryId更新为2
		shopCategory.setShopCategoryId(2L);

		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("ArtisanUP");
		shop.setShopDesc("ArtisanDescUP");
		shop.setShopAddr("NanJingUP");
		shop.setPhone("123456UP");
		shop.setShopImg("/xxx/xxx/UP");
		shop.setPriority(66);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("Waring UP");

		int effectNum = shopDao.updateShop(shop);

		Assert.assertEquals(effectNum, 1);
		logger.debug("update  successfully");

	}

	@Test
	@Ignore
	public void testSelectShopById() {
		Shop shop = shopDao.selectShopById(30L);
		Assert.assertNotNull(shop);
		Assert.assertEquals("优乐美", shop.getShopName());
		Assert.assertEquals("北京", shop.getArea().getAreaName());
		Assert.assertEquals("奶茶", shop.getShopCategory().getShopCategoryName());

		System.out.println(shop.toString());
	}

	@Test
	@Ignore
	public void testSelectShopListAndCount() {

		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		PersonInfo personInfo = new PersonInfo();
		List<Shop> shopList = null;

		/**
		 * 可输入的查询条件： 1.商铺名（要求模糊查询） 2.区域Id 3.商铺状态 4.商铺类别 5.owner
		 * 
		 * 
		 * 首先按照单个条件进行单元测试，然后组合测试
		 **/
		
		// 1.商铺名（要求模糊查询）
		Shop shopCondition = new Shop();
		shopCondition.setShopName("咖啡");
		
		// 1.1 数据库中只有3条数据符合 ，我们分页条件 取出5条，即全部取出 验证rowIndex 和 pageSize
		shopList = shopDao.selectShopList(shopCondition, 0, 5);
		Assert.assertEquals(3, shopList.size());
		
		int count1 = shopDao.selectShopCount(shopCondition);
		Assert.assertEquals(3, count1);

		// 1.2 数据库中只有3条数据符合 ，我们分页条件 取出2条，即取出前两条 验证rowIndex 和 pageSize
		shopList = shopDao.selectShopList(shopCondition, 0, 2);
		Assert.assertEquals(2, shopList.size());
		
		// 总数依然是3条
		int count2 = shopDao.selectShopCount(shopCondition);
		Assert.assertEquals(3, count2);

		// 为了不影响测试， 新实例化出来一个Shop

		// 2.区域Id 库表中符合条件的记录有10条 areaId=1 10条 areaId=2 3条
		Shop shopCondition2 = new Shop();
		area.setAreaId(1);
		shopCondition2.setArea(area);
		shopList = shopDao.selectShopList(shopCondition2, 0, 99);
		Assert.assertEquals(10, shopList.size());
		
		area.setAreaId(2);
		shopCondition2.setArea(area);
		shopList = shopDao.selectShopList(shopCondition2, 0, 99);
		Assert.assertEquals(3, shopList.size());
		
		
		// 3.商铺状态 EnableStatus=0 12条 EnableStatus=1 1条
		Shop shopCondition3 = new Shop();
		shopCondition3.setEnableStatus(0);
		shopList = shopDao.selectShopList(shopCondition3, 0, 99);
		Assert.assertEquals(12, shopList.size());

		shopCondition3.setEnableStatus(1);
		shopList = shopDao.selectShopList(shopCondition3, 0, 99);
		Assert.assertEquals(1, shopList.size());
				
				
		// 4.商铺类别
		// shop_category_id = 1 9条数据
		// shop_category_id = 2 3条数据
		// shop_category_id = 3 1条数据
		Shop shopCondition4 = new Shop();

		shopCategory.setShopCategoryId(1L);
		shopCondition4.setShopCategory(shopCategory);
		shopList = shopDao.selectShopList(shopCondition4, 0, 99);
		Assert.assertEquals(9, shopList.size());

		shopCategory.setShopCategoryId(2L);
		shopCondition4.setShopCategory(shopCategory);
		shopList = shopDao.selectShopList(shopCondition4, 0, 99);
		Assert.assertEquals(3, shopList.size());

		shopCategory.setShopCategoryId(3L);
		shopCondition4.setShopCategory(shopCategory);
		shopList = shopDao.selectShopList(shopCondition4, 0, 99);
		Assert.assertEquals(1, shopList.size());
		
		// 5.owner_id=1 13条 其余0条
		Shop shopCondition5 = new Shop();
		personInfo.setUserId(1L);
		shopCondition5.setOwner(personInfo);
		shopList = shopDao.selectShopList(shopCondition5, 0, 99);
		Assert.assertEquals(13, shopList.size());

		personInfo.setUserId(877L);
		shopCondition5.setOwner(personInfo);
		shopList = shopDao.selectShopList(shopCondition5, 0, 99);
		Assert.assertEquals(0, shopList.size());

		// 组合场景不全面，仅列几个

		// 组合场景 owner_id =1 shop_name like %咖啡%
		Shop shopCondition6 = new Shop();
		personInfo.setUserId(1L);
		shopCondition6.setOwner(personInfo);
		shopCondition6.setShopName("咖啡");
		shopList = shopDao.selectShopList(shopCondition6, 0, 99);
		Assert.assertEquals(3, shopList.size());

		int count6 = shopDao.selectShopCount(shopCondition6);
		Assert.assertEquals(3, count6);

		// 组合场景 area_id =1 shop_name like %咖啡% owner_id=1
		Shop shopCondition7 = new Shop();
		personInfo.setUserId(1L);
		area.setAreaId(1);
		shopCondition7.setOwner(personInfo);
		shopCondition7.setArea(area);
		shopCondition7.setShopName("咖啡");
		shopList = shopDao.selectShopList(shopCondition7, 0, 99);
		Assert.assertEquals(2, shopList.size());

		int count7 = shopDao.selectShopCount(shopCondition7);
		Assert.assertEquals(2, count7);
	}

	@Test
	public void testSelectShopListAndCount2() {

		// 根据表中的数据 共计6条数据
		// 其中 2条 属于 【咖啡】二级商铺类别
		// 其中 2条 属于 【奶茶】二级商铺类别
		// 其中 2条 属于 【考研辅导】二级商铺类别

		// 而 【咖啡】和【奶茶】二级商铺类别 属于 【美食饮品】一级商铺类别

		// 模拟用户点击【美食饮品】，查询出来属于 【美食饮品】下面的商铺 ,
		// 期望符合条件的数据为 2条 属于 【咖啡】二级商铺类别的店铺 + 2条 属于 【奶茶】二级商铺类别 的店铺
		
		/**
		 * 
		 * <!-- 选择了某个大类，列举出该大类下面的全部商店 以parent_id来筛选 --> <if
		 * test="shopCondition.shopCategory != null and
		 * shopCondition.shopCategory.parent != null and
		 * shopCondition.shopCategory.parent.shopCategoryId != null "> and
		 * s.shop_category_id in ( select shop_category_id from tb_shop_category
		 * where parent_id = #{shopCondition.shopCategory.parent.shopCategoryId}
		 * ) </if>
		 * 
		 * 
		 * 
		 */
		
		
		
		Shop shopCondition = new Shop();
		// 模拟
		ShopCategory childShopCategory = new ShopCategory();
		ShopCategory parentShopCategory = new ShopCategory();
		// 设置父类的shopCategoryId
		parentShopCategory.setShopCategoryId(3L);
		// 设置父子关系
		childShopCategory.setParent(parentShopCategory);

		// 设置目录
		shopCondition.setShopCategory(childShopCategory);

		List<Shop> shoplist = shopDao.selectShopList(shopCondition, 0, 5);
		int count = shopDao.selectShopCount(shopCondition);
		Assert.assertEquals(4, shoplist.size());
		Assert.assertEquals(4, count);
		for (Shop shop : shoplist) {
			System.out.println(shop.toString());
		}

	}
}
