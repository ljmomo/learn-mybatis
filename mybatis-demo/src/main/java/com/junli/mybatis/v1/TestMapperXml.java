package com.junli.mybatis.v1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lijun
 * @since 2018-04-02 13:24
 */
public class TestMapperXml {

    public static final  String namespace = "com.junli.mybatis.mapper.TestMapper";

    public static final Map<String,String> methodSqlMapping = new HashMap<>();

    static {
        methodSqlMapping.put("selectByPrimaryKey","select * from test where id =%d");
    }
}
