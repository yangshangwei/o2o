package com.artisan.o2o.service;

import java.util.List;

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
		for (HeadLine headLine : headLineList) {
			System.out.println("0<<<<" + headLine);
		}

		// 查询可用的头条信息
		headLineConditon.setEnableStatus(1);
		headLineList = headLineService.queryHeadLineList(headLineConditon);
		for (HeadLine headLine : headLineList) {
			System.out.println("**------>" + headLine);
		}

		// 再次查询 状态为0 和 1的头条信息 ，确保从缓存中取数据
		// 查询不可用的头条信息
		headLineConditon.setEnableStatus(0);
		headLineList = headLineService.queryHeadLineList(headLineConditon);
		for (HeadLine headLine : headLineList) {
			System.out.println("0>>>>" + headLine);
		}

		// 查询可用的头条信息
		headLineConditon.setEnableStatus(1);
		headLineList = headLineService.queryHeadLineList(headLineConditon);
		for (HeadLine headLine : headLineList) {
			System.out.println("||------>" + headLine);
		}

	}
}
