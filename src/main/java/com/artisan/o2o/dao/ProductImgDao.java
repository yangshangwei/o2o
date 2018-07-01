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

	/**
	 * 
	 * 
	 * @Title: deleteProductImgById
	 * 
	 * @Description: 删除商品对应的商品详情图片
	 * 
	 * @param productId
	 * 
	 * @return: int
	 */
	int deleteProductImgById(long productId);
<<<<<<< HEAD

	/**
	 * 
	 * 
	 * @Title: selectProductImgList
	 * 
	 * @Description: 根据productId查询商铺对应的图片详情信息
	 * 
	 * @param productId
	 * 
	 * @return: List<ProductImg>
	 */
	List<ProductImg> selectProductImgList(long productId);
=======
>>>>>>> abece8c742dc4dd06914e6995ed487762f32260e
}
