package com.junli.mybatis.v1;

import java.lang.reflect.Proxy;

/**
 * @author lijun
 * @since 2018-04-02 13:08
 */
public class LJConfiguration {

    public <T> T getMapper(Class<T> tClass, LJSqlSession ljSqlSession) {
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{tClass},new LJMapperProxy(ljSqlSession));
    }

}
