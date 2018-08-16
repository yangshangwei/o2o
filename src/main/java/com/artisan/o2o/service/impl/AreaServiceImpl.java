package com.artisan.o2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artisan.o2o.cache.JedisUtil;
import com.artisan.o2o.dao.AreaDao;
import com.artisan.o2o.entity.Area;
import com.artisan.o2o.service.AreaService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 
 * @ClassName: AreaServiceImpl
 * 
 * @Description: @Service标注的服务层
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年5月14日 下午9:06:45
 */
@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	AreaDao areaDao;

	@Autowired
	private JedisUtil.Strings jedisStrings;
	@Autowired
	private JedisUtil.Keys jedisKeys;

	private static String AREALISTKEY = "arealist";

	@Override
	public List<Area> getAreaList() throws JsonParseException, JsonMappingException, IOException {
		// 定义redis中的key
		String key = AREALISTKEY;
		// 定义接收对象
		List<Area> areaList = null;
		// 定义jackson数据转换操作类
		ObjectMapper mapper = new ObjectMapper();
		// 判断redis中key是否存在
		// 1、存在，直接从redis中取数据
		// 2、不存在，查询数据库，获取数据，并把数据更新到redis中
		if (!jedisKeys.exists(key)) {
			areaList = areaDao.queryArea();
			// 将相关的实体类集合转换成string,存入redis里面对应的key中
			String jsonString = mapper.writeValueAsString(areaList);
			jedisStrings.set(key, jsonString);
		} else {
			// 若存在，则直接从redis里面取出相应数据
			String jsonString = jedisStrings.get(key);
			// 指定要将string转换成的集合类型
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
			// 将相关key对应的value里的的string转换成java对象的实体类集合
			areaList = mapper.readValue(jsonString, javaType);
		}
		return areaList;
	}

}
