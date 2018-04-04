package com.junli.mybatis.v1;

/**
 * @author lijun
 * @since 2018-04-02 13:07
 */
public class LJSqlSession {

    private  LJConfiguration ljConfiguration;
    private  LJExecutor ljExecutor;

    public LJSqlSession(LJConfiguration ljConfiguration, LJExecutor ljExecutor) {
        this.ljConfiguration = ljConfiguration;
        this.ljExecutor = ljExecutor;
    }

    public <T> T getMappper(Class<T> tClass){
       return ljConfiguration.getMapper(tClass, this);
    }

    public <T> T selectOne(String sql,String paramters){
        return  ljExecutor.query(sql,paramters);
    }
}
