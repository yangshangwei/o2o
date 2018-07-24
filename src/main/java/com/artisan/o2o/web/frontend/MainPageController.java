package com.artisan.o2o.web.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.artisan.o2o.entity.HeadLine;
import com.artisan.o2o.entity.ShopCategory;
import com.artisan.o2o.enums.HeadLineStateEnum;
import com.artisan.o2o.enums.ShopCategoryStateEnum;
import com.artisan.o2o.service.HeadLineService;
import com.artisan.o2o.service.ShopCategoryService;

@Controller
@RequestMapping("/frontend")
public class MainPageController {

	@Autowired
	private HeadLineService headLineService;
	@Autowired
	private ShopCategoryService shopCategoryService;

	@RequestMapping(value = "/listmainpage", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> listMainPage() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<HeadLine> headLineList = new ArrayList<HeadLine>();
		try {
			// 查询状态为1的可见的headLine信息
			HeadLine headLineConditon = new HeadLine();
			headLineConditon.setEnableStatus(1);
			headLineList = headLineService.queryHeadLineList(headLineConditon);
			
			modelMap.put("headLineList", headLineList);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("errMsg", HeadLineStateEnum.INNER_ERROR.getStateInfo());
		}
		
		try{
			// 查询parentId为null的一级类别
			shopCategoryList = shopCategoryService.getShopCategoryList(null);
			modelMap.put("shopCategoryList", shopCategoryList);
		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", ShopCategoryStateEnum.INNER_ERRO.getStateInfo());
		}

		modelMap.put("success", true);

		return modelMap;
	}
}
