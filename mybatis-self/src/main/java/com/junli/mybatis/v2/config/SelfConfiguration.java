package com.junli.mybatis.v2.config;

import com.junli.mybatis.v2.beans.Test;
import com.junli.mybatis.v2.mapper.SelfMapperProxy;
import com.junli.mybatis.v2.session.SelfSqlSession;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author lijun
 * @since 2018-04-03 9:12
 */
public class SelfConfiguration {


    private String scanPath;

    private MapperRegistory  mapperRegistory = new MapperRegistory();

    public SelfConfiguration scanPath(String scanPath) {
        this.scanPath = scanPath;
        return this;
    }

    public void build() throws IOException {
        if (null == scanPath || scanPath.length() < 1) {
            throw new RuntimeException("scan path is required .");
        }
        //这里要做的是扫描包下的所有Mapper 把他注册到MapperRegistory 里面  这里为了简单就不做扫描(TODO)
        MapperRegistory.methodSqlMapping.put("com.junli.mybatis.v2.mappers.TestMapper.selectByPrimaryKey",
                new MapperData("select * from test where id = %d",Test.class));
    }


    public <T> T getMapper(Class<T> tClass, SelfSqlSession selfSqlSession) {
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{tClass},
                new SelfMapperProxy(selfSqlSession));
    }

    public String getScanPath() {
        return scanPath;
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }

    public MapperRegistory getMapperRegistory() {
        return mapperRegistory;
    }

    public void setMapperRegistory(MapperRegistory mapperRegistory) {
        this.mapperRegistory = mapperRegistory;
    }
}
