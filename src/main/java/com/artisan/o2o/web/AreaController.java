package com.artisan.o2o.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.artisan.o2o.entity.Area;
import com.artisan.o2o.service.AreaService;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
	Logger logger = LoggerFactory.getLogger(AreaController.class);

	@Autowired
	AreaService areaService;

	@RequestMapping(value = "/listArea", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAreas() {
		logger.info("-----begin getAreas------");
		Long beginTimeLong = System.currentTimeMillis();

		Map<String, Object> map = new HashMap<String, Object>();
		List<Area> areaList = new ArrayList<Area>();
		try {
			areaList = areaService.getAreaList();
			map.put("total", areaList.size());
			map.put("rows", areaList);

			for (Area area : areaList) {
				System.out.println("区域:" + area);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("errMsg", e.getMessage().toString());
			logger.error("exception happpens , desc [{}] ", e.getMessage());
		}


		Long endTimeLong = System.currentTimeMillis();
		logger.debug("cost [{}ms]", endTimeLong - beginTimeLong);
		logger.info("-----end getAreas------");
		return map;
	}

}
