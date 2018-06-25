package com.artisan.o2o.dao;

import java.util.List;

import com.artisan.o2o.entity.ProductImg;

public interface ProductImgDao {

	/**
	 * 
	 * 
	 * @Title: batchInsertProductImg
	 * 
	 * @Description: 一个商品下可能拥有多个图片，所以这里是批量新增商品图片
	 * 
	 * @param productImgList
	 * 
	 * @return: int
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);
}
