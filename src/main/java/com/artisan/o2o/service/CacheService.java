package com.artisan.o2o.service;

public interface CacheService {
	
	
	/**
	 * 
	 * 
	 * @Title: removeFromCache
	 * 
	 * @Description: 根据缓存的前缀清理匹配的全部缓存
	 * 
	 * @param keyPrefix
	 * 
	 * @return: void
	 */
	void removeFromCache(String keyPrefix);
}
