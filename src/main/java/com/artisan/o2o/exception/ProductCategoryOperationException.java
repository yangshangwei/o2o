package com.artisan.o2o.exception;

/**
 * 
 * 
 * @ClassName: ProductCategoryOperationException
 * 
 * @Description: 继承RuntimeException,便于异常时候的回滚。 保持所有的操作在一个事务中。
 * 
 *               这样在标注了@Transactional事务的方法中，出现了异常，才会回滚数据。
 * 
 *               默认情况下，如果在事务中抛出了未检查异常（继承自 RuntimeException 的异常）或者 Error，则 Spring
 *               将回滚事务；除此之外，Spring 不会回滚事务。
 * 
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年6月21日 上午12:22:44
 */
public class ProductCategoryOperationException extends RuntimeException {

	private static final long serialVersionUID = 6500682256313143297L;

	public ProductCategoryOperationException(String message) {
		super(message);
	}

}
