package com.artisan.o2o.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artisan.o2o.dao.LocalAuthDao;
import com.artisan.o2o.entity.LocalAuth;
import com.artisan.o2o.service.LocalAuthService;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {

	@Autowired
	private LocalAuthDao localAuthDao;

	@Override
	public int registerLocalAuth(LocalAuth localAuth) {
		return localAuthDao.insertLocalAuth(localAuth);
	}

	@Override
	public LocalAuth queryLocalAuthByUserNameAndPwd(String userName, String password) {
		return localAuthDao.queryLocalByUserNameAndPwd(userName, password);
	}

}
