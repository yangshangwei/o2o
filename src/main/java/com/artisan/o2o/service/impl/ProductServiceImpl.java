package com.artisan.o2o.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artisan.o2o.dao.ProductDao;
import com.artisan.o2o.dao.ProductImgDao;
import com.artisan.o2o.dto.ImageHolder;
import com.artisan.o2o.dto.ProductExecution;
import com.artisan.o2o.entity.Product;
import com.artisan.o2o.entity.ProductImg;
import com.artisan.o2o.enums.ProductStateEnum;
import com.artisan.o2o.exception.ProductOperationException;
import com.artisan.o2o.service.ProductService;
import com.artisan.o2o.util.FileUtil;
import com.artisan.o2o.util.ImageUtil;

/**
 * 
 * 
 * @ClassName: ProductServiceImpl
 * 
 * @Description: @Service 标识的服务层
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年6月25日 上午3:59:56
 */

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	@Autowired
	ProductImgDao productImgDao;

	@Deprecated
	@Override
	public ProductExecution addProductDep(Product product, InputStream prodImgIns, String prodImgName, List<InputStream> prodImgDetailInsList, List<String> prodImgDetailNameList)
			throws ProductOperationException {
		// 废弃的方法
		return null;
	}

	/**
	 * 注意事务控制@Transactional
	 * 
	 * 
	 * 步骤如下：
	 * 
	 * 1.处理商品的缩略图，获取相对路径，为了调用dao层的时候写入 tb_product中的 img_addr字段有值
	 * 
	 * 2.写入tb_product ，获取product_id
	 * 
	 * 3.集合product_id 批量处理商品详情图片
	 * 
	 * 4.将商品详情图片 批量更新到 tb_proudct_img表
	 * 
	 */
	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> prodImgDetailList) throws ProductOperationException {
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null && product.getProductCategory().getProductCategoryId() != null) {
			// 设置默认的属性 1 展示
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			product.setEnableStatus(1);
			// 如果文件的输入流和文件名不为空，添加文件到特定目录，并且将相对路径设置给product,这样product就有了ImgAddr，为下一步的插入tb_product提供了数据来源
			if (imageHolder != null) {
				addProductImg(product, imageHolder);
			}
			try {
				// 写入tb_product
				int effectNum = productDao.insertProduct(product);
				if (effectNum <= 0 ) {
					throw new ProductOperationException("商品创建失败");
				}
				// 如果添加商品成功，继续处理商品详情图片，并写入tb_product_img
				if (prodImgDetailList != null && prodImgDetailList.size() > 0) {
					addProductDetailImgs(product, prodImgDetailList);
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new ProductOperationException("商品创建失败：" + e.getMessage());
			}

		} else {
			return new ProductExecution(ProductStateEnum.NULL_PARAMETER);
		}
	}


	/**
	 * 
	 * 
	 * @Title: addProductImg
	 * 
	 * @Description: 将商品的缩略图写到特定的shopId目录，并将imgAddr属性设置给product
	 * 
	 * @param product
	 * @param imageHolder
	 * 
	 * @return: void
	 */
	private void addProductImg(Product product, ImageHolder imageHolder) {
		// 根据shopId获取图片存储的相对路径
		String relativePath = FileUtil.getShopImagePath(product.getShop().getShopId());
		// 添加图片到指定的目录
		String relativeAddr = ImageUtil.generateThumbnails(imageHolder, relativePath);
		// 将relativeAddr设置给product
		product.setImgAddr(relativeAddr);
	}

	/**
	 * 
	 * 
	 * @Title: addProductDetailImgs
	 * 
	 * @Description: 处理商品详情图片，并写入tb_product_img
	 * 
	 * @param product
	 * @param prodImgDetailList
	 * 
	 * @return: void
	 */
	private void addProductDetailImgs(Product product, List<ImageHolder> prodImgDetailList) {
		String relativePath = FileUtil.getShopImagePath(product.getShop().getShopId());
		// 生成图片详情的图片,大一些，并且不添加水印，所以另外写了一个方法，基本和generateThumbnails相似
		List<String> imgAddrList = ImageUtil.generateNormalImgs(prodImgDetailList, relativePath);

		if (imgAddrList != null && imgAddrList.size() > 0) {
			List<ProductImg> productImgList = new ArrayList<ProductImg>();
			for (String imgAddr : imgAddrList) {
				ProductImg productImg = new ProductImg();
				productImg.setImgAddr(imgAddr);
				productImg.setProductId(product.getProductId());
				productImg.setCreateTime(new Date());
				productImgList.add(productImg);
			}
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品详情图片失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品详情图片失败:" + e.toString());
			}
		}
	}

}
