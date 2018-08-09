package com.artisan.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * 
 * @ClassName: EncryptPropertyPlaceholderConfigurer
 * 
 * @Description: 继承PropertyPlaceholderConfigurer，重写convertProperty
 * 
 * @author: Mr.Yang
 * 
 * @date: 2018年8月9日 下午9:20:04
 */
public class EncryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	// 需要加密的字段数组
	private String[] encryptPropNames = { "jdbc.username", "jdbc.password" };

	/**
	 * 对关键的属性进行转换
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			// 解密
			String decryptValue = DESUtils.getDecryptString(propertyValue);
			return decryptValue;
		} else {
			return propertyValue;
		}
	}

	/**
	 * 
	 * 
	 * @Title: isEncryptProp
	 * 
	 * @Description: 判断该属性是否加密
	 * 
	 * @param propertyName
	 * 
	 * @return: boolean
	 */
	private boolean isEncryptProp(String propertyName) {
		for (String encryptpropertyName : encryptPropNames) {
			if (encryptpropertyName.equals(propertyName))
				return true;
		}
		return false;
	}
}
