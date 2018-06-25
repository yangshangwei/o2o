package com.artisan.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.ProductImg;

public class ProductImgDaoTest extends BaseTest {

	@Autowired
	private ProductImgDao productImgDao;

	@Test
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

}
