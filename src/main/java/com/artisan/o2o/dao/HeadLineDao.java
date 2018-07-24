package com.artisan.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.artisan.o2o.entity.HeadLine;

public interface HeadLineDao {
	/**
	 * 
	 * 
	 * @Title: selectHeadLineList
	 * 
	 * @Description: 根据enable_status查询符合条件的头条信息
	 * 
	 * @param headLineConditon
	 * @return
	 * 
	 * @return: List<HeadLine>
	 */
	List<HeadLine> selectHeadLineList(@Param("headLineConditon") HeadLine headLineConditon);
}
