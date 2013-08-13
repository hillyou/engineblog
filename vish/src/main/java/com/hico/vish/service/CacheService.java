package com.hico.vish.service;

import java.util.Collections;
import java.util.Map;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;
import net.sf.jsr107cache.CacheStatistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CacheService {
	private final static Logger LOGGER=LoggerFactory.getLogger(CacheService.class);
//	private final static CacheService CACHE = new CacheService();
//
//	private CacheService() {
//	}
//
//	public static CacheService getInstance() {
//		return CACHE;
//	}
	
	public static Cache getCache() {
		Cache cache=null;
        try {
        	CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
        } catch (CacheException e) {
        	LOGGER.warn("inital cache error: {}",e.getMessage());
        }
        return cache;
	}
	
	public static Cache getCache(Map props) {
		Cache cache=null;
        try {
        	CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(props);
        } catch (CacheException e) {
        	LOGGER.warn("inital cache error: {}",e.getMessage());
        }
        return cache;
	}

	public static CacheStatistics  getCacheStatistics(Cache cache) {
		CacheStatistics stats = cache.getCacheStatistics();
		return stats;
	}
}
