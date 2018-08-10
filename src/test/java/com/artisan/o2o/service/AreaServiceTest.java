package com.artisan.o2o.service;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.Area;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class AreaServiceTest extends BaseTest {

	@Autowired
	AreaService areaService;

	@Test
	public void testGetAreaList() throws JsonParseException, JsonMappingException, IOException {
		List<Area> areaList = areaService.getAreaList();
		Assert.assertEquals("北京", areaList.get(0).getAreaName());
	}
}
