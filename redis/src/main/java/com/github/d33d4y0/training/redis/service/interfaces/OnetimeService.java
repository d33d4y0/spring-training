package com.github.d33d4y0.training.redis.service.interfaces;

import java.util.Map;

/* in reality we you can use ObjectService with 
 * remove method instead of this service
 */
public interface OnetimeService {

	public void putAll(Map<String, Object> map);

	public void put(String key, Object value);

	public Boolean exists(String key);

	public Object get(String key);
}
