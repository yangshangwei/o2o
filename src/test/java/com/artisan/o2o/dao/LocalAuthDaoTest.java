package com.artisan.o2o.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.artisan.o2o.BaseTest;
import com.artisan.o2o.entity.LocalAuth;
import com.artisan.o2o.entity.PersonInfo;
import com.artisan.o2o.util.MD5;

public class LocalAuthDaoTest extends BaseTest {

	@Autowired
	LocalAuthDao localAuthDao;
	
	@Test
	public void testInsertLocalAuth() {
		
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);

		localAuth.setUserName("Artisan");
		localAuth.setPassword(MD5.getMd5("123456"));
		localAuth.setCreateTime(new Date());
		localAuth.setLastEditTime(new Date());

		localAuth.setPersonInfo(personInfo);

		int effectNum = localAuthDao.insertLocalAuth(localAuth);
		Assert.assertEquals(1, effectNum);

	}

	@Test
	public void testQueryLocalByUserNameAndPwd() {
		LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd("artisan", MD5.getMd5("123456"));
		System.out.println(localAuth.toString());
	}
}
