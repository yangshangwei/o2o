package com.artisan.o2o.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.Area;

public class AreaServiceTest extends BaseTest {

	@Autowired
	AreaService areaService;

	@Test
	public void testGetAreaList() {
		List<Area> areaList = areaService.getAreaList();
		Assert.assertEquals("上海", areaList.get(0).getAreaName());
	}
}
