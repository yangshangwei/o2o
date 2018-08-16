package com.artisan.o2o.service;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.Area;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class AreaServiceTest extends BaseTest {

	@Autowired
	AreaService areaService;

	@Autowired
	CacheService cacheService;

	@Test
	public void testGetAreaList() throws JsonParseException, JsonMappingException, IOException {
		// 首次从db中加载
		List<Area> areaList = areaService.getAreaList();
		for (Area area : areaList) {
			System.out.println("||---->" + area.toString());
		}

		// 再次查询从redis中获取
		areaList = areaService.getAreaList();
		for (Area area : areaList) {
			System.out.println("**---->" + area.toString());
		}
		// 清除缓存
		cacheService.removeFromCache(AreaService.AREALISTKEY);

		// 再次查询 从db中获取
		areaList = areaService.getAreaList();
		for (Area area : areaList) {
			System.out.println("**---->" + area.toString());
		}

		// 再次查询从redis中获取
		areaList = areaService.getAreaList();
		for (Area area : areaList) {
			System.out.println("**---->" + area.toString());
		}
	}

}
