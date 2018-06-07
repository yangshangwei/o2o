package com.artisan.o2o.util;

/**
 * 
 * 
 * @ClassName: PageCalculator
 * 
 * @Description: 将前台使用的pageIndex 转换为 dao层使用的 rowIndex
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年6月7日 上午12:24:38
 */
public class PageCalculator {

	public static int calculateRowIndex(int pageIndex, int pageSize) {
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}
}
