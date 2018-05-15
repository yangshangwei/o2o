package com.artisan.o2o.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Autowired
	AreaService areaService;

	@RequestMapping(value = "/listArea", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAreas() {
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
		}
		return map;
	}

}
