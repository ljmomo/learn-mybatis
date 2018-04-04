package com.junli.mybatis.v2.session;

import com.junli.mybatis.v2.config.MapperData;
import com.junli.mybatis.v2.config.SelfConfiguration;
import com.junli.mybatis.v2.executor.SelfExecutor;

/**
 * SelfSqlSession
 * 要提供 getMapper和selectOne方法
 *
 * @author lijun
 * @since 2018-04-03 9:06
 */
public class SelfSqlSession {

    /**
     * 配置类
     */
    private SelfConfiguration configuration;

    /**
     * 执行器
     */
    private SelfExecutor executor;

    public SelfSqlSession(SelfConfiguration configuration, SelfExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 调用 configuration 的getMapper方法
     *
     * @param tClass Mapper接口
     * @param <T>    Mapper接口
     * @return Mapper接口
     */
    public <T> T getMapper(Class<T> tClass) {
        return configuration.getMapper(tClass, this);
    }

    /**
     * @param mapperData sql 和 返回 实体映射
     * @param paramters  参数
     * @param <T>
     * @return 返回 实体
     */
    public <T> T selectOne(MapperData mapperData, String paramters) {
        return executor.query(mapperData, paramters);
    }

    public SelfConfiguration getConfiguration() {
        return configuration;
    }
}
