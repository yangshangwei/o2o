package com.artisan.o2o.service;

import java.io.InputStream;
import java.util.List;

import com.artisan.o2o.dto.ImageHolder;
import com.artisan.o2o.dto.ProductExecution;
import com.artisan.o2o.entity.Product;
import com.artisan.o2o.exception.ProductOperationException;

/**
 * 
 * 
 * @ClassName: ProductService
 * 
 * @Description: ProductService
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年6月25日 上午1:59:40
 */
public interface ProductService {

	/**
	 * 
	 * 
	 * @Title: addProductDep
	 * 
	 * @Description: 新增商品 。 因为无法从InputStream中获取文件的名称，所以需要指定文件名
	 * 
	 *               需要传入的参数太多，我们将InputStream 和 ImgName封装到一个实体类中，便于调用。
	 * 
	 *               及早进行优化整合，避免后续改造成本太大
	 * 
	 * @param product
	 *            商品信息
	 * @param prodImgIns
	 *            商品缩略图输入流
	 * @param prodImgName
	 *            商品缩略图名称
	 * @param prodImgDetailIns
	 *            商品详情图片的输入流
	 * @param prodImgDetailName
	 *            商品详情图片的名称
	 * @return
	 * @throws ProductOperationException
	 * 
	 * @return: ProductExecution
	 */

	@Deprecated
	ProductExecution addProductDep(Product product, InputStream prodImgIns, String prodImgName, List<InputStream> prodImgDetailInsList, List<String> prodImgDetailNameList)
			throws ProductOperationException;

	/**
	 * 
	 * 
	 * @Title: addProduct
	 * 
	 * @Description: 重构后的addProduct
	 * 
	 * @param product
	 *            产品信息
	 * @param imageHolder
	 *            产品缩略图的封装信息
	 * @param prodImgDetailList
	 *            产品详情图片的封装信息
	 * 
	 * @throws ProductOperationException
	 * 
	 * @return: ProductExecution
	 */
	ProductExecution addProduct(Product product, ImageHolder imageHolder, List<ImageHolder> prodImgDetailList) throws ProductOperationException;


	/**
	 * 
	 * 
	 * @Title: queryProductById
	 * 
	 * @Description: 根据productId查询product
	 * 
	 * @param productId
	 * 
	 * @return: Product
	 */
	Product queryProductById(long productId);

	/**
	 * 
	 * 
	 * @Title: modifyProduct
	 * 
	 * @Description: TODO
	 * 
	 * @param product
	 *            产品信息
	 * @param imageHolder
	 *            产品缩略图的封装信息
	 * @param prodImgDetailList
	 *            产品详情图片的封装信息
	 * @throws ProductOperationException
	 * 
	 * @return: ProductExecution
	 */
	ProductExecution modifyProduct(Product product, ImageHolder imageHolder, List<ImageHolder> prodImgDetailList) throws ProductOperationException;
}
