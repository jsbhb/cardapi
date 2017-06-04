package com.fireCloud.tradCity.basic;

import org.springframework.stereotype.Component;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
* @author  wqy
* @fun 系统缓存，使用默认配置进行创建，容量最大500，不设置过期时间
* @date 2015年4月7日 上午11:22:50
*/ 
@Component
public class SysCache {
	
	//初始化缓存管理
	private static final CacheManager manager = CacheManager.create();
	
	//初始化缓存
	private static final Cache cache = new Cache("sysCache", 500, false, true, 0, 0);

	static {
		//缓存加入管理
		manager.addCache(cache);
	}

	/**
	 * 存入缓存
	 * @param key
	 * @param obj
	 */
	public void put(String key, Object obj) {
		cache.put(new Element(key, obj));
	}

	/**
	 * 取出缓存
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		Element ele = cache.get(key);
		return ele == null ? null : ele.getObjectValue();
	}

	/**
	 * 清除缓存
	 * @param key
	 * @return
	 */
	public boolean remove(String key) {
		return cache.remove(key);
	}

}
