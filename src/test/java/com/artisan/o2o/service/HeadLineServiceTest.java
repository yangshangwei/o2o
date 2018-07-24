package com.artisan.o2o.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.HeadLine;

public class HeadLineServiceTest extends BaseTest {

	@Autowired
	private HeadLineService headLineService;

	@Test
	public void testQueryHeadLineList() {
		HeadLine headLineConditon = new HeadLine();
		// 状态 0 不可用 1 可用
		headLineConditon.setEnableStatus(0);

		// 查询不可用的头条信息
		List<HeadLine> headLineList = headLineService.queryHeadLineList(headLineConditon);
		Assert.assertEquals(2, headLineList.size());
		for (HeadLine headLine : headLineList) {
			System.out.println(headLine);
		}
		// 查询可用的头条信息
		headLineConditon.setEnableStatus(1);
		headLineList = headLineService.queryHeadLineList(headLineConditon);
		Assert.assertEquals(3, headLineList.size());
		for (HeadLine headLine : headLineList) {
			System.out.println(headLine);
		}

	}
}
