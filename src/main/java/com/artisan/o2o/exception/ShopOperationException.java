package com.artisan.o2o.exception;

/**
 * 
 * 
 * @ClassName: ShopOperationException
 * 
 * @Description: 继承自RuntimeException ，这样在标注了@Transactional事务的方法中，出现了异常，才回回滚数据。
 * 
 *               默认情况下，如果在事务中抛出了未检查异常（继承自 RuntimeException 的异常）或者 Error，则 Spring
 *               将回滚事务；除此之外，Spring 不会回滚事务。
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月21日 下午5:37:53
 */
public class ShopOperationException extends RuntimeException {

	private static final long serialVersionUID = 6860566652051914211L;


	public ShopOperationException(String message) {
		super(message);
	}

}
