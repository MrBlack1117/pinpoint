package com.profiler.modifier.tomcat.interceptors;

import java.util.Arrays;
import java.util.logging.Logger;

import com.profiler.LifeCycleEventListener;
import com.profiler.interceptor.StaticAfterInterceptor;
import com.profiler.logging.LoggingUtils;
import com.profiler.util.StringUtils;

/**
 *
 */
public class StandardServiceStartInterceptor implements StaticAfterInterceptor {

    private final Logger logger = Logger.getLogger(StandardServiceStartInterceptor.class.getName());
    private final boolean isDebug = LoggingUtils.isDebug(logger);

    private LifeCycleEventListener lifeCycleEventListener;

    public StandardServiceStartInterceptor(LifeCycleEventListener lifeCycleEventListener) {
        this.lifeCycleEventListener = lifeCycleEventListener;
    }

    @Override
    public void after(Object target, String className, String methodName, String parameterDescription, Object[] args, Object result) {
        if (isDebug) {
            logger.fine("after " + StringUtils.toString(target) + " " + className + "." + methodName + parameterDescription + " args:" + Arrays.toString(args) + " result:" + result);
        }
		// if (!InterceptorUtils.isSuccess(result)) {
		// return;
		// }
        lifeCycleEventListener.start();
    }
}
