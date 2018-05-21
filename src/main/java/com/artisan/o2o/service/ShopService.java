package com.artisan.o2o.service;

import java.io.File;

import com.artisan.o2o.dto.ShopExecution;
import com.artisan.o2o.entity.Shop;

public interface ShopService {

	ShopExecution addShop(Shop shop, File shopFile);

}
