package com.artisan.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artisan.o2o.dao.AreaDao;
import com.artisan.o2o.entity.Area;
import com.artisan.o2o.service.AreaService;

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

	@Override
	public List<Area> getAreaList() {
		return areaDao.queryArea();
	}

}
