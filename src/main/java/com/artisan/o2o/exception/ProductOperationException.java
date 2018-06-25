package com.artisan.o2o.exception;

/**
 * 
 * 
 * @ClassName: ProductOperationException
 * 
 * @Description: 继承自RuntimeException ，这样在标注了@Transactional事务的方法中，出现了异常，才会回滚数据。
 * 
 *               默认情况下，如果在事务中抛出了未检查异常（继承自 RuntimeException 的异常）或者 Error，则 Spring
 *               将回滚事务；除此之外，Spring 不会回滚事务。
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年6月25日 下午1:46:23
 */
public class ProductOperationException extends RuntimeException {

	private static final long serialVersionUID = -6981952073033881834L;

	public ProductOperationException(String message) {
		super(message);
	}

}
