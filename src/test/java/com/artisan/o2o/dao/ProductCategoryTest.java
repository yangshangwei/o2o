package com.artisan.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.ProductCategory;

/**
 * 
 * 
 * @ClassName: ProductCategoryTest
 * 
 * @Description: Junit 4.11里增加了指定测试方法执行顺序的特性 .
 * 
 *               测试类的执行顺序可通过对测试类添加注解@FixMethodOrder(value) 来指定,其中value 为执行顺序
 * 
 *               三种执行顺序可供选择：
 * 
 *               默认（MethodSorters.DEFAULT）,
 *               默认顺序由方法名hashcode值来决定，如果hash值大小一致，则按名字的字典顺序确定
 *               由于hashcode的生成和操作系统相关
 *               (以native修饰），所以对于不同操作系统，可能会出现不一样的执行顺序，在某一操作系统上，多次执行的顺序不变
 * 
 *               按方法名（ MethodSorters.NAME_ASCENDING）【推荐】,
 *               按方法名称的进行排序，由于是按字符的字典顺序，所以以这种方式指定执行顺序会始终保持一致；
 *               不过这种方式需要对测试方法有一定的命名规则，如 测试方法均以testNNN开头（NNN表示测试方法序列号 001-999）
 * 
 *               JVM（MethodSorters.JVM）
 *               按JVM返回的方法名的顺序执行，此种方式下测试方法的执行顺序是不可预测的，即每次运行的顺序可能都不一样
 * 
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年6月21日 下午11:55:45
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryTest extends BaseTest {
	
	@Autowired
	ProductCategoryDao productCategoryDao;
	
	@Test
	public void testB_SelectProductCategoryList() {
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
	public void testA_BatchInsertProductCategory() {

		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryName("product1");
		productCategory1.setProductCategoryDesc("product1_desc");
		productCategory1.setPriority(99);
		productCategory1.setCreateTime(new Date());
		productCategory1.setLastEditTime(new Date());
		productCategory1.setShopId(5L);

		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("product2");
		productCategory2.setProductCategoryDesc("product2_desc");
		productCategory2.setPriority(98);
		productCategory2.setCreateTime(new Date());
		productCategory2.setLastEditTime(new Date());
		productCategory2.setShopId(5L);

		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory1);
		productCategoryList.add(productCategory2);

		int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		Assert.assertEquals(2, effectNum);

	}

	@Test
	public void testC_DeleteProductCategory() {
		// 查询出来shopId=5的商铺下面全部的商品目录
		List<ProductCategory> productCategoryList = productCategoryDao.selectProductCategoryList(5L);
		// 遍历循环删除
		for (ProductCategory productCategory : productCategoryList) {
			if ("product1".equals(productCategory.getProductCategoryName()) || "product2".equals(productCategory.getProductCategoryName())) {
				int effectNum = productCategoryDao.deleteProductCategory(productCategory.getProductCategoryId(), 5L);
				assertEquals(1, effectNum);
			}
		}
	}

}
