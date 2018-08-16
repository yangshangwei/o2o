package com.artisan.o2o.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artisan.o2o.cache.JedisUtil;
import com.artisan.o2o.dao.ShopCategoryDao;
import com.artisan.o2o.entity.ShopCategory;
import com.artisan.o2o.service.ShopCategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Autowired
	private JedisUtil.Keys jedisKeys;

	@Autowired
	private JedisUtil.Strings jedisStrings;

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {

		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		
		// 定义redis中key
		String key = "shopcategory";
		// 定义jackson数据转换操作类
		ObjectMapper mapper = new ObjectMapper();
		
		// 根据mapper中的查询条件，拼装shopcategory的key
		if (shopCategory == null) {
			// 查询条件为空，列出所有的首页大类，即parentId为空的店铺类别
			key = key + "_allfirstlevelshopcategory";
		} else if (shopCategory != null && shopCategory.getParent() != null && shopCategory.getParent().getShopCategoryId() != null) {
			// 列出某个parentId下面的所有子类
			key = key + "_parent" + shopCategory.getParent().getShopCategoryId();
		} else if (shopCategory != null) {
			// 列出所有的子类，不管属于哪个类
			key = key + "_allsecondlevelshopcategory";
		}

		// 如果缓存中不出在则从DB中查询并缓存到redis中
		if (!jedisKeys.exists(key)) {
			try {
				// 从DB中加载
				shopCategoryList = shopCategoryDao.queryShopCategoryList(shopCategory);
				// 将相关的实体类集合转换成string,存入redis里面对应的key中
				String jsonString = mapper.writeValueAsString(shopCategoryList);
				jedisStrings.set(key, jsonString);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				logger.error("实体类集合转换string存入redis异常{}", e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("其他异常{}", e.getMessage());
			}
		} else {
			// 否则直接从redis中获取
			try {
				String jsonString = jedisStrings.get(key);
				// 指定要将string转换成的集合类型
				JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
				// 将相关key对应的value里的的string转换成java对象的实体类集合
				shopCategoryList = mapper.readValue(jsonString, javaType);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("异常{}", e.getMessage());
			}
		}
		return shopCategoryList;
	}

}
