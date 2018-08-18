package com.artisan.o2o.web.shopadmin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.artisan.o2o.entity.LocalAuth;
import com.artisan.o2o.service.LocalAuthService;
import com.artisan.o2o.util.HttpServletRequestUtil;
import com.artisan.o2o.util.MD5;
import com.artisan.o2o.util.VerifyCodeUtil;

@Controller
@RequestMapping(value = "/shop", method = { RequestMethod.GET, RequestMethod.POST })
public class LocalAuthController {

	@Autowired
	private LocalAuthService localAuthService;

	@RequestMapping("localauthlogincheck")
	@ResponseBody
	public Map<String, Object> localAuthLoginCheck(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// 是否需要校验的标志
		boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");

		// 验证码校验
		if (needVerify && !VerifyCodeUtil.verifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码不正确,请重新输入");
			return modelMap;
		}
		// 获取用户输入的用户名+密码
		String userName = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");

		if (userName != null && password != null) {
			// 数据库中的密码是MD加密的，所以需要先将密码加密，然后再调用后台的接口
			password = MD5.getMd5(password);
			LocalAuth localAuth = localAuthService.queryLocalAuthByUserNameAndPwd(userName, password);
			if (localAuth != null) {
				// 将personInfo写入session中
				request.getSession().setAttribute("user", localAuth.getPersonInfo());
				modelMap.put("success", true);
				modelMap.put("errMsg", "登录成功");
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码不正确");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码不能为空");
		}
		return modelMap;
	}

}
