package com.junli.mybatis.v2.executor;

import com.junli.mybatis.v2.config.MapperData;
import com.junli.mybatis.v2.config.SelfConfiguration;
import com.junli.mybatis.v2.statement.SelfStatementHandler;

/**
 * @author lijun
 * @since 2018-04-03 13:13
 */
public class SimpleSelfExecutor implements SelfExecutor {
   private SelfConfiguration selfConfiguration;

    public SimpleSelfExecutor(SelfConfiguration selfConfiguration) {
        this.selfConfiguration = selfConfiguration;
    }

    @Override
    public <T> T query(MapperData mapperData, Object paramters) {
        //初始化StatementHandler --> ParameterHandler --> ResultSetHandler
        SelfStatementHandler handler = new SelfStatementHandler(selfConfiguration);
        return (T)handler.query(mapperData,paramters);
    }
}
