package com.junli.mybatis.v2.executor;

import com.junli.mybatis.v2.config.MapperData;
import com.junli.mybatis.v2.config.SelfConfiguration;
import com.junli.mybatis.v2.statement.SelfStatementHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijun
 * @since 2018-04-03 14:47
 */
public class SelfCachingExecutor implements SelfExecutor {

    private SelfConfiguration configuration;

    private SimpleSelfExecutor delegate;

    private Map<String,Object> localCache = new HashMap();

    public SelfCachingExecutor(SimpleSelfExecutor delegate) {
        this.delegate = delegate;
    }

    public SelfCachingExecutor(SelfConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T query(MapperData mapperData, Object paramters) {
        SelfStatementHandler handler = new SelfStatementHandler(configuration);
        Object result = localCache.get(mapperData.getSql());
        if( null != result){
            System.out.println("缓存命中");
            return (T) result;
        }
        result =  (T) delegate.query(mapperData,paramters);
        localCache.put(mapperData.getSql(),result);
        return (T) result;
    }
}
