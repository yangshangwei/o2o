package com.artisan.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/shopadmin")
public class ShopAdminController {

	@RequestMapping(value = "/shopregist", method = RequestMethod.GET)
	public String shopRegist(){
		return "shop/shopoperation";
	}
}
