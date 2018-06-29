package com.artisan.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/shopadmin")
public class ShopAdminController {

	@RequestMapping(value = "/shopoperation", method = RequestMethod.GET)
	public String shopOperation() {
		return "shop/shopoperation";
	}

	@RequestMapping(value = "/shoplist", method = RequestMethod.GET)
	public String shopList() {
		return "shop/shoplist";
	}

	@RequestMapping(value = "/shopmanagement", method = RequestMethod.GET)
	public String shopManagement() {
		return "shop/shopmanagement";
	}

	@RequestMapping(value = "/productcategorymanage", method = RequestMethod.GET)
	public String productCategoryManage() {
		return "shop/productcategorymanage";
	}

	@RequestMapping(value = "/productoperation", method = RequestMethod.GET)
	public String productOperation() {
		return "shop/productoperation";
	}
}
