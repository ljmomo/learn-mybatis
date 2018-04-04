package com.junli.mybatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lijun
 * @since 2018-04-02 13:14
 */
public class LJMapperProxy implements InvocationHandler {
    private  LJSqlSession ljSqlSession ;
    public LJMapperProxy(LJSqlSession ljSqlSession) {
        this.ljSqlSession = ljSqlSession;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().getName().equals(TestMapperXml.namespace)){
            String sql = TestMapperXml.methodSqlMapping.get(method.getName());
            return ljSqlSession.selectOne(sql,String.valueOf(args[0]));
        }
        return method.invoke(this,args);
    }
}
