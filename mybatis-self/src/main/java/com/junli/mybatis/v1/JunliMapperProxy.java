package com.junli.mybatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lijun
 * @since 2018-04-02 9:30
 */
public class JunliMapperProxy implements InvocationHandler {
    private JunliSqlSession junliSqlSession;

    public JunliMapperProxy(JunliSqlSession junliSqlSession) {
        this.junliSqlSession = junliSqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().getName().equals(JunliConfiguration.TestMapperXml.namespace)){
            String sql = JunliConfiguration.TestMapperXml.methodSqlMapping.get(method.getName());
            return junliSqlSession.selectOne(sql,String.valueOf(args[0]));
        }
        return method.invoke(this,args);
    }
}
