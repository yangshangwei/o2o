package com.artisan.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.ProductImg;

/**
 * 
 * 
 * @ClassName: ProductImgDaoTest
 * 
 * @Description: 测试类的执行顺序可通过对测试类添加注解@FixMethodOrder(value) 来指定
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年6月30日 下午3:28:28
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest {

	@Autowired
	private ProductImgDao productImgDao;

	/**
	 * 加入@Ignore 可以不执行该单元测试方法
	 */
	@Test
	@Ignore
	public void testBatchInsertProductImg() {
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("/xiaogongjiang/xxxx");
		productImg1.setImgDesc("商品详情图片1");
		productImg1.setPriority(99);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(2L);
		
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("/artisan/xxxx");
		productImg2.setImgDesc("商品详情图片2");
		productImg2.setPriority(98);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(2L);

		// 添加到productImgList中
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);

		// 调用接口批量新增商品详情图片
		int effectNum = productImgDao.batchInsertProductImg(productImgList);
		Assert.assertEquals(2, effectNum);
	}

	@Test
	public void testA_BatchInsertProductImg() {
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("/xxx/xxx");
		productImg1.setImgDesc("商品详情图片1x");
		productImg1.setPriority(88);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(3L);

		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("/yyy/yyyy");
		productImg2.setImgDesc("商品详情图片2y");
		productImg2.setPriority(66);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(3L);

		// 添加到productImgList中
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);

		// 调用接口批量新增商品详情图片
		int effectNum = productImgDao.batchInsertProductImg(productImgList);
		Assert.assertEquals(2, effectNum);
	}

	@Test
	public void testC_DeleteProductImgById() {
		Long productId = 3L;
		int effectNum = productImgDao.deleteProductImgById(productId);
		Assert.assertEquals(2, effectNum);
	}

	@Test
	public void testB_SelectProdcutImgById() {
		Long productId = 3L;
		List<ProductImg> productImgList = productImgDao.selectProductImgList(productId);
		Assert.assertEquals(2, productImgList.size());

		for (ProductImg productImg : productImgList) {
			System.out.println(productImg.toString());
		}
	}
}
