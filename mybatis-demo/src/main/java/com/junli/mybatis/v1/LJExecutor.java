package com.junli.mybatis.v1;

/**
 * @author lijun
 * @since 2018-04-02 13:08
 */
public interface LJExecutor {
    public <T> T query(String sql, String paramers);
}
