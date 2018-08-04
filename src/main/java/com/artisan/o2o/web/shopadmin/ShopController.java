package com.artisan.o2o.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.artisan.o2o.dto.ImageHolder;
import com.artisan.o2o.dto.ShopExecution;
import com.artisan.o2o.entity.Area;
import com.artisan.o2o.entity.PersonInfo;
import com.artisan.o2o.entity.Shop;
import com.artisan.o2o.entity.ShopCategory;
import com.artisan.o2o.enums.ShopStateEnum;
import com.artisan.o2o.exception.ShopOperationException;
import com.artisan.o2o.service.AreaService;
import com.artisan.o2o.service.ShopCategoryService;
import com.artisan.o2o.service.ShopService;
import com.artisan.o2o.util.HttpServletRequestUtil;
import com.artisan.o2o.util.VerifyCodeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/shopadmin")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private ShopCategoryService shopCategoryService;

	@Autowired
	private AreaService areaservice;

	/**
	 * 
	 * 
	 * @Title: registerShop
	 * 
	 * @Description:
	 * 
	 * @param request
	 *            因为是接收前端的请求，而前端的信息都封装在HttpServletRequest中，
	 *            所以需要解析HttpServletRequest，获取必要的参数
	 * 
	 *            1. 接收并转换相应的参数，包括shop信息和图片信息 2. 注册店铺 3. 返回结果给前台
	 * @return
	 * 
	 * @return: Map<String,Object>
	 */

	@RequestMapping(value = "/registshop", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		// 0. 验证码校验
		if (!VerifyCodeUtil.verifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码不正确");
			return modelMap;
		}
		// 1. 接收并转换相应的参数，包括shop信息和图片信息

		// 1.1 shop信息

		// shopStr 是和前端约定好的参数值，后端从request中获取request这个值来获取shop的信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		// 使用jackson-databind 将json转换为pojo
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			// 将json转换为pojo
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			e.printStackTrace();
			// 将错误信息返回给前台
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// 1.2 图片信息 基于Apache Commons FileUpload的文件上传

		// Spring MVC中的 图片存在CommonsMultipartFile中
		CommonsMultipartFile shopImg = null;
		// 从request的本次会话中的上线文中获取图片的相关内容
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// shopImg是和前端约定好的变量名
			shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");
		} else {
			// 将错误信息返回给前台
			modelMap.put("success", false);
			modelMap.put("errMsg", "图片信息为空");
			return modelMap;
		}

		// 2. 注册店铺
		if (shop != null && shopImg != null) {
			// Session
			// 店主persionInfo的信息，肯定要登录才能注册店铺。
			// 所以这部分信息我们从session中获取，尽量不依赖前端,这里暂时时不具备条件，后续改造，先硬编码，方便单元测试
			// PersonInfo personInfo = new PersonInfo();
			// personInfo.setUserId(1L);

			// 注册店铺之前登录，登录成功后，约定好将user这个key 设置到session中，
			// 这里通过key就可以取到PersonInfo的信息
			PersonInfo personInfo = (PersonInfo) request.getSession().getAttribute("user");
			shop.setOwner(personInfo);
			// 注册店铺

			// se = shopService.addShop(shop, shopImg); 改造前的调用方式
			// 这个时候，我们从前端获取到的shopImg是CommonsMultipartFile类型的，如何将CommonsMultipartFile转换为file.
			// 网上也有将CommonsMultipartFile转换为File的方法，并通过maxInMemorySize的设置尽量不产生临时文件
			// 这里我们换个思路,因为CommonsMultipartFile可以获取InputStream,Thumbnailator又可以直接处理输入流
			// 因为InputStream中我们无法得到文件的名称，而thumbnail中需要根据文件名来获取扩展名，所以还要再加一个参数String类型的fileName
			// 既然第二个和第三个参数都是通过shopImg获取的，为什么不直接传入一个shopImg呢？
			// 主要是为了service层单元测测试的方便，因为service层很难实例化出一个CommonsMultipartFile类型的实例
			ShopExecution se = null;;
			try {
				// se = shopService.addShop(shop, shopImg.getInputStream(),
				// shopImg.getOriginalFilename());
				se = shopService.addShop(shop, new ImageHolder(shopImg.getInputStream(), shopImg.getOriginalFilename()));
				if (se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					modelMap.put("errMsg", "注册成功");
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (Exception e) {
				e.printStackTrace();
				modelMap.put("success", false);
				modelMap.put("errMsg", ShopStateEnum.INNER_ERROR.getStateInfo());
			}
		} else {
			// 将错误信息返回给前台
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入店铺信息");
		}
		return modelMap;
	}
	
	
	/**
	 * 
	 * 
	 * @Title: getshopInitInfo
	 * 
	 * @Description: 初始化区域信息 和 ShopCategory信息,返回给前台表单页面
	 * 
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getshopInitInfo() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategoryList = null;
		List<Area> areaList = null;
		try {
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaservice.getAreaList();
			// 返回success shopCategoryList areaList,前端通过
			// data.success来判断从而展示shopCategoryList和areaList的数据
			modelMap.put("success", true);
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	/**
	 * 
	 * 
	 * @Title: getShopInfoById
	 * 
	 * @Description: 根据shopId获取shop信息， 接收前端的请求，获取shopId ，所以入参为HttpServletRequest
	 * @ResponseBody 不需要VIEW展现层模块，直接显示到客户端的内容。 将内容或对象作为 HTTP 响应正文返回
	 * 
	 * @param request
	 * 
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value = "/getshopinfobyId", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopInfoById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// shopId 为和前端约定好的变量
		int shopId = HttpServletRequestUtil.getInt(request, "shopId");
		try {
			if (shopId >= 0) {
				// 查询 ，按照设计，我们希望前端页面下拉列表中可以修改区域信息，所以需要查询出来全量的区域列表
				// 对已ShopCategory而言，我们DAO层的SQL已经将shop_category_id和
				// shop_category_name 查询出来，前端设置到对应的属性上即可。没有必要全部查询出来。
				Shop shop = shopService.getShopById(shopId);
				List<Area> areaList = areaservice.getAreaList();

				modelMap.put("success", true);
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "shopId不合法");
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	/**
	 * 
	 * 
	 * @Title: modifyshop
	 * 
	 * @Description:
	 * 
	 * @param request
	 *            因为是接收前端的请求，而前端的信息都封装在HttpServletRequest中，
	 *            所以需要解析HttpServletRequest，获取必要的参数
	 * 
	 *            1. 接收并转换相应的参数，包括shop信息和图片信息 2.修改店铺 3. 返回结果给前台
	 * @return
	 * 
	 * @return: Map<String,Object>
	 */

	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyshop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		// 0. 验证码校验
		if (!VerifyCodeUtil.verifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码不正确");
			return modelMap;
		}
		// 1. 接收并转换相应的参数，包括shop信息和图片信息

		// 1.1 shop信息

		// shopStr 是和前端约定好的参数值，后端从request中获取request这个值来获取shop的信息
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		// 使用jackson-databind 将json转换为pojo
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			// 将json转换为pojo
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			e.printStackTrace();
			// 将错误信息返回给前台
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}

		// 1.2 图片信息 基于Apache Commons FileUpload的文件上传 （ 修改商铺信息 图片可以不更新）

		// Spring MVC中的 图片存在CommonsMultipartFile中
		CommonsMultipartFile shopImg = null;
		// 从request的本次会话中的上线文中获取图片的相关内容
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// shopImg是和前端约定好的变量名
			shopImg = (CommonsMultipartFile) multipartRequest.getFile("shopImg");
		}

		// 2. 修改店铺
		if (shop != null && shop.getShopId() != null) {
			// Session 部分的 PersonInfo 修改商铺是不需要的设置的。
			// 修改店铺
			ShopExecution se = null;
			try {
				if (shopImg != null) {
					se = shopService.modifyShop(shop, new ImageHolder(shopImg.getInputStream(), shopImg.getOriginalFilename()));
				} else {
					se = shopService.modifyShop(shop, null);
				}
				// 成功
				if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
					modelMap.put("errMsg", "修改成功");
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (Exception e) {
				e.printStackTrace();
				modelMap.put("success", false);
				modelMap.put("errMsg", "ModifyShop Error");
			}
		} else {
			// 将错误信息返回给前台
			modelMap.put("success", false);
			modelMap.put("errMsg", "ShopId不合法");
		}
		return modelMap;
	}
	
	
	/**
	 * 
	 * 
	 * @Title: getShopList
	 * 
	 * @Description: 从session中获取当前person拥有的商铺列表
	 * 
	 * @param request
	 * @return
	 * 
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopList(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 现在还没有做登录模块，因此session中并没有用户的信息，先模拟一下登录 要改造TODO
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		personInfo.setName("小工匠");
		request.getSession().setAttribute("user", personInfo);
		// 从session中获取user信息
		personInfo = (PersonInfo) request.getSession().getAttribute("user");
		
		try {
			Shop shopCondition = new Shop();
			shopCondition.setOwner(personInfo);
			ShopExecution se = shopService.getShopList(shopCondition, 1, 99);
			modelMap.put("success", true);
			modelMap.put("shopList", se.getShopList());
			modelMap.put("user", personInfo);
		} catch (ShopOperationException e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}

	/**
	 * 
	 * 
	 * @Title: shopManagement
	 * 
	 * @Description: 从商铺列表页面中，点击“进入”按钮进入
	 *               某个商铺的管理页面的时候，对session中的数据的校验从而进行页面的跳转，是否跳转到店铺列表页面或者可以直接操作该页面
	 * 
	 *               访问形式如下
	 *               http://ip:port/o2o/shopadmin/shopmanagement?shopId=xxx
	 * 
	 * @return
	 * 
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value = "/getshopmanageInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getShopManageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取shopId
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		// 如果shopId不合法
		if (shopId < 0) {
			// 尝试从当前session中获取
			Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
			if (currentShop == null) {
				// 如果当前session中也没有shop信息,告诉view层 重定向
				modelMap.put("redirect", true);
				modelMap.put("url", "/o2o/shopadmin/shoplist");
			}else{
				// 告诉view层 进入该页面
				modelMap.put("redirect", false);
				modelMap.put("shopId", currentShop.getShopId());
			}
		} else { // shopId合法的话
			Shop shop = new Shop();
			shop.setShopId(shopId);
			// 将currentShop放到session中
			request.getSession().setAttribute("currentShop", shop);
			modelMap.put("redirect", false);
		}

		return modelMap;
	}

}
