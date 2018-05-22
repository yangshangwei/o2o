package com.artisan.o2o.service;

import java.io.InputStream;

import com.artisan.o2o.dto.ShopExecution;
import com.artisan.o2o.entity.Shop;

public interface ShopService {

	ShopExecution addShop(Shop shop, InputStream shopFileInputStream, String fileName);

}
