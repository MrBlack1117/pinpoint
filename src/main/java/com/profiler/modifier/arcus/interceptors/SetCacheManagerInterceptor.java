package com.profiler.modifier.arcus.interceptors;

import java.util.Arrays;
import java.util.logging.Logger;

import com.profiler.logging.LoggingUtils;
import net.spy.memcached.CacheManager;
import net.spy.memcached.MemcachedClient;

import com.profiler.interceptor.StaticBeforeInterceptor;
import com.profiler.util.MetaObject;
import com.profiler.util.StringUtils;

/**
 * 
 * @author netspider
 * 
 */
public class SetCacheManagerInterceptor implements StaticBeforeInterceptor {

	private final Logger logger = Logger.getLogger(SetCacheManagerInterceptor.class.getName());
    private final boolean isDebug = LoggingUtils.isDebug(logger);

	private MetaObject<String> getServiceCode = new MetaObject<String>("__getServiceCode");
	private MetaObject<String> setServiceCode = new MetaObject<String>("__setServiceCode", String.class);

	@Override
	public void before(Object target, String className, String methodName, String parameterDescription, Object[] args) {
		if (isDebug) {
			logger.fine("before " + StringUtils.toString(target) + " " + className + "." + methodName + parameterDescription + " args:" + Arrays.toString(args));
		}
		
		CacheManager cm = (CacheManager) args[0];
		String serviceCode = getServiceCode.invoke(cm);
		
		setServiceCode.invoke((MemcachedClient) target, serviceCode);
	}
}
