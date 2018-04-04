package com.junli.mybatis.v1;

/**
 * @author lijun
 * @since 2018-04-01 20:55
 */
public interface JunliExecutor {
    <T> T query(String statement, String params);
}
