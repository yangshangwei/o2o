package com.artisan.o2o.service;

import com.artisan.o2o.entity.LocalAuth;

public interface LocalAuthService {

	int registerLocalAuth(LocalAuth localAuth);

	LocalAuth queryLocalAuthByUserNameAndPwd(String userName, String password);
}
