package com.junli.mybatis.v2.mapper;

import com.junli.mybatis.v2.config.MapperData;
import com.junli.mybatis.v2.session.SelfSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lijun
 * @since 2018-04-03 10:13
 */
public class SelfMapperProxy implements InvocationHandler{

    private SelfSqlSession selfSqlSession;
    public SelfMapperProxy(SelfSqlSession selfSqlSession) {
        this.selfSqlSession = selfSqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String namespace = method.getDeclaringClass().getName()+ "."+ method.getName();
        MapperData mapperData = selfSqlSession.getConfiguration().getMapperRegistory().get(namespace);
        if (mapperData!=null){
            System.out.println(String.format("SQL [ %s ], parameter [%s] ", mapperData.getSql(), args[0]));
            return selfSqlSession.selectOne(mapperData,String.valueOf(args[0]));
        }
        return method.invoke(proxy,args);
    }
}
