package com.junli.mybatis.v1;

/**
 * @author lijun
 * @since 2018-04-01 20:53
 */
public class JunliSqlSession {

    /**
     * 配置信息
     */
    JunliConfiguration junliConfiguration;

    /**
     * 执行器
     */
    JunliExecutor junliExecutor;

    public JunliSqlSession(JunliConfiguration junliConfiguration, JunliExecutor junliExecutor) {
        this.junliConfiguration = junliConfiguration;
        this.junliExecutor = junliExecutor;
    }

    public <T> T getMapper(Class<T> tClass) {
        return junliConfiguration.getMapper(tClass,this);
    }

    public <T> T selectOne(String statement, String params) {
        return junliExecutor.query(statement, params);
    }


}
