package com.artisan.o2o.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.LocalAuth;
import com.artisan.o2o.util.MD5;

public class LocalAuthServiceImplTest extends BaseTest {
	
	@Autowired
	private LocalAuthService localAuthService;
	
	@Test
	public void testQueryLocalAuthByUserNameAndPwd() {
		LocalAuth localAuth = localAuthService.queryLocalAuthByUserNameAndPwd("Artisan", MD5.getMd5("123456"));
		Assert.assertEquals("Artisan", localAuth.getUserName());
		System.out.println(localAuth.toString());
	}
}
