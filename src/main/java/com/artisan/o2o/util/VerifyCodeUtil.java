package com.artisan.o2o.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerifyCodeUtil {

	private static final Logger logger = LoggerFactory.getLogger(VerifyCodeUtil.class);

	/**
	 * 
	 * 
	 * @Title: verifyCode
	 * 
	 * @Description:验证码校验
	 * 
	 * @param request
	 *            前端HttpServletRequest
	 * @return
	 * 
	 * @return: boolean
	 */
	public static boolean verifyCode(HttpServletRequest request) {
		// 图片中的验证码
		String verifyCodeExpected = (String) request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		logger.debug("verifyCodeExpected:{}", verifyCodeExpected);
		// 用户输入的验证码
		String verifyCodeActual = HttPServletRequestUtil.getString(request,
				"verifyCodeActual");
		logger.debug("verifyCodeActual:{}", verifyCodeActual);
		if (verifyCodeActual == null
				|| !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)) {
			return false;
		}
		return true;
	}
}
