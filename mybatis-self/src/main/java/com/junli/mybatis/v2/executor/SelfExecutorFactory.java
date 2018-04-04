package com.junli.mybatis.v2.executor;

import com.junli.mybatis.v2.config.SelfConfiguration;

/**
 * @author lijun
 * @since 2018-04-03 14:44
 */
public class SelfExecutorFactory {
    private static final String SIMPLE = "SIMPLE";
    private static final String CACHING = "CACHING";

    public static SelfExecutor DEFAULT(SelfConfiguration configuration) {
        return get(SIMPLE, configuration);
    }

    public static SelfExecutor get(String key, SelfConfiguration configuration) {
        if (SIMPLE.equalsIgnoreCase(key)) {
            return new SimpleSelfExecutor(configuration);
        }
        if (CACHING.equalsIgnoreCase(key)) {
            return new SelfCachingExecutor(new SimpleSelfExecutor(configuration));
        }
        throw new RuntimeException("no executor found");
    }

    public enum ExecutorType {
        SIMPLE,CACHING
    }
}
