package com.github.d33d4y0.training.redis.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TimeService {

	public Void putAll(Map<String, LocalDateTime> map);

	public LocalDateTime put(String key, boolean ignoreExists);

	public LocalDateTime remove(String key, boolean ignoreNotFound);

	public Boolean exists(String key);

	public LocalDateTime get(String key);

	public Map<String, LocalDateTime> all();

	public Set<String> keys();

	public Void clear();

	public List<String> today();

	public List<String> yesterday();

	public List<String> fromYesterday();

	public List<String> last15Mins();

	public List<String> last30Mins();

	public List<String> lastHour();

	public List<String> last7Days();

	public List<String> last15Days();

	public List<String> thisMonth();

	public List<String> from(LocalDateTime from);

	public List<String> fromTo(LocalDateTime from, LocalDateTime to);
}
