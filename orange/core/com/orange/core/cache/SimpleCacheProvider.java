package com.orange.core.cache;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * simple cache provider use map
 */
public class SimpleCacheProvider implements CacheProvider {
	public SimpleCacheProvider() {

	}

	private static SimpleCacheProvider instance = new SimpleCacheProvider();
	private static Map<String, Serializable> cacheContainer = Maps.newHashMap();

	public SimpleCacheProvider getInstance() {
		return instance;
	}

	public void put(String key, Serializable cacheObject) {
		cacheContainer.put(key, cacheObject);
	}

	public Serializable get(String key) {
		return cacheContainer.get(key);
	}

	public void remove(String key) {
		cacheContainer.remove(key);
	}

	public void clear() {
		cacheContainer.clear();
	}
}
