package com.artisan.o2o.service;

import java.util.List;

import com.artisan.o2o.entity.HeadLine;

public interface HeadLineService {
	
	// redis key的前缀，抽取到接口层，方便使用
	public static final String HEADLINEKEY = "headline";
		
	
	/**
	 * 
	 * 
	 * @Title: queryHeadLineList
	 * 
	 * @Description: 查询headLine
	 * 
	 * @param headLineConditon
	 * 
	 * @return: List<HeadLine>
	 */
	List<HeadLine> queryHeadLineList(HeadLine headLineConditon);
}
