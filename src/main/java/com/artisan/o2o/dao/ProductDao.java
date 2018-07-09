package com.artisan.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.artisan.o2o.entity.Product;

public interface ProductDao {

	/**
	 * 
	 * 
	 * @Title: insertProduct
	 * 
	 * @Description: 增加商品
	 * 
	 * @param product
	 * 
	 * @return: int
	 */
	int insertProduct(Product product);

	/**
	 * 
	 * 
	 * @Title: selectProductById
	 * 
	 * @Description: 根据productId查询product
	 * 
	 * @param productId
	 * 
	 * @return: Product
	 */
	Product selectProductById(long productId);

	/**
	 * 
	 * 
	 * @Title: updateProduct
	 * 
	 * @Description: 修改商品
	 * 
	 * @param product
	 * 
	 * @return: int
	 */
	int updateProduct(Product product);

	/**
	 * 
	 * 
	 * @Title: selectProductList
	 * 
	 * @Description: 支持分页功能的查询product
	 * 
	 *               需要支持根据商品名称（支持模糊查询）、商品状态、shopId、商品类别的查询及组合查询
	 * 
	 * @param productCondition
	 * @param rowIndex
	 *            从第几行开始取
	 * @param pageSize
	 *            返回多少行数据（页面上的数据量）
	 * 
	 *            比如 rowIndex为1,pageSize为5 即为 从第一行开始取，取5行数据
	 * 
	 * @return: List<Product>
	 */
	List<Product> selectProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 
	 * 
	 * @Title: selectCountProduct
	 * 
	 * @Description: 按照条件查询 符合前台传入的条件的商品的总数
	 * 
	 * @param productCondition
	 * @return
	 * 
	 * @return: int
	 */
	int selectCountProduct(@Param("productCondition") Product productCondition);

	/**
	 * 
	 * 
	 * @Title: updateProductCategory2Null
	 * 
	 * @Description: 
	 *               删除productCategory的时候，需要先将tb_product中的该productCategoryId置为null
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * 
	 * @return: int
	 */
	int updateProductCategory2Null(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
